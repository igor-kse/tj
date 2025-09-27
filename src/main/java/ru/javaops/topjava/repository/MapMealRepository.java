package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.util.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MapMealRepository implements MealRepository {

    private static final String MEAL_NOT_NULL_MESSAGE = "Meal cannot be null";
    private static final String ID_NOT_NULL_MESSAGE = "id cannot be null";

    private static final Map<Integer, Meal> mealStorage = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    public MapMealRepository() {
    }

    public MapMealRepository(int seed) {
        counter.set(seed);
    }


    @Override
    public void save(Meal meal) {
        Utils.assureNotNull(meal, MEAL_NOT_NULL_MESSAGE);

        Integer id = meal.getId();
        if (id == null) {
            id = counter.incrementAndGet();
        }
        mealStorage.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    @Override
    public boolean update(Meal meal) {
        Utils.assureNotNull(meal, MEAL_NOT_NULL_MESSAGE);
        Utils.assureNotNull(meal.getId(), ID_NOT_NULL_MESSAGE);

        var success = mealStorage.replace(meal.getId(), meal);
        return success != null;
    }

    @Override
    public Meal findById(int id) {
        return mealStorage.get(id);
    }

    @Override
    public void deleteById(int id) {
        mealStorage.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return mealStorage.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .toList();
    }
}
