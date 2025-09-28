package ru.javaops.topjava.repository.memory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MapMealRepository implements MealRepository {

    private static final Map<Long, Map<Long, Meal>> mealStorage = new ConcurrentHashMap<>();
    private static final AtomicLong lastId = new AtomicLong(0);

    private static final Logger log = LoggerFactory.getLogger(MapMealRepository.class);

    private static final Comparator<Meal> MEAL_DATETIME_DESC = Comparator.comparing(Meal::getDateTime).reversed();

    @Override
    public Meal save(Meal meal, long ownerId) {
        log.info("Saving meal: {}", meal);
        var meals = mealStorage.computeIfAbsent(ownerId, id -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(lastId.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, meal1) -> meal);
    }

    @Override
    public Meal find(long id, long ownerId) {
        log.info("Getting meal by id: {} for owner {}", id, ownerId);
        var meals = mealStorage.get(ownerId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public boolean delete(long id, long ownerId) {
        log.info("Deleting meal by id: {}; for owner: {}", id, ownerId);
        var meals = mealStorage.get(ownerId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public List<Meal> getAll(long ownerId) {
        log.info("Getting all meals of owner: {}", ownerId);
        var meals = mealStorage.get(ownerId);
        return meals == null || meals.isEmpty()
                ? Collections.emptyList()
                : meals.values().stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).toList();
    }

    public List<Meal> getBetweenHalfOpen(long ownerId, LocalDateTime start, LocalDateTime end) {
        var dtStart = (start == null) ? LocalDateTime.MIN : start;
        var dtEnd = (start == null) ? LocalDateTime.MAX : end;

        log.debug("Getting all meals between {} and {} for owner {}", dtStart, dtEnd, ownerId);

        if (!dtStart.isBefore(dtEnd)) {
            return List.of();
        }

        var meals = mealStorage.get(ownerId);
        if (meals == null || meals.isEmpty()) {
            return List.of();
        }

        return meals.values().stream()
                .filter(meal -> {
                    var dt = meal.getDateTime();
                    return (!dt.isBefore(dtStart) && dt.isBefore(dtEnd));
                })
                .sorted(MEAL_DATETIME_DESC)
                .toList();
    }
}
