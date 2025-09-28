package ru.javaops.topjava.util;

import ru.javaops.topjava.mapper.MealMapper;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.to.MealTo;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    private MealsUtil() {
    }

    public static List<MealTo> getTos(List<Meal> meals, int caloriesPerDay) {
        return filteredByPredicate(meals, caloriesPerDay, meal -> true);
    }

    public static List<MealTo> getFilteredTos(List<Meal> meals, int caloriesPerDay, LocalTime start, LocalTime end) {
        return filteredByPredicate(meals, caloriesPerDay, meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), start, end));
    }

    public static List<MealTo> filteredByPredicate(List<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        var caloriesPerDays = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .filter(filter)
                .map(meal -> MealMapper.map(meal, caloriesPerDays.get(meal.getDate()) > caloriesPerDay))
                .toList();
    }
}
