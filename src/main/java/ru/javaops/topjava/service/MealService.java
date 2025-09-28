package ru.javaops.topjava.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.repository.MealRepository;
import ru.javaops.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javaops.topjava.util.ValidationUtil.checkIsNew;
import static ru.javaops.topjava.util.ValidationUtil.checkNotFound;

@Service
public class MealService {

    private static final String MEAL_NOT_FOUND_MESSAGE = "Meal not found: id = %d";

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal get(long id, long ownerId) {
        var meal = mealRepository.find(id, ownerId);
        return checkNotFound(meal, String.format(MEAL_NOT_FOUND_MESSAGE, id));
    }

    public void save(Meal meal, long ownerId) {
        checkIsNew(meal);
        meal.setUserId(ownerId);
        mealRepository.save(meal, ownerId);
    }

    public void update(Meal meal, long ownerId) {
        meal.setUserId(ownerId);
        var updated = mealRepository.save(meal, ownerId);
        checkNotFound(updated, String.format(MEAL_NOT_FOUND_MESSAGE, meal.getId()));
    }

    public void delete(long id, long ownerId) {
        boolean deleted = mealRepository.delete(id, ownerId);
        checkNotFound(deleted, String.format(MEAL_NOT_FOUND_MESSAGE, id));
    }

    public List<Meal> getAllMeals(long ownerId) {
        return mealRepository.getAll(ownerId);
    }

    public List<Meal> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, long userId) {
        LocalDateTime start = DateTimeUtil.atStartOfDayOrMin(startDate);
        LocalDateTime end = DateTimeUtil.atStartOfNextDayOrMax(endDate);
        return mealRepository.getBetweenHalfOpen(userId, start, end);
    }


}
