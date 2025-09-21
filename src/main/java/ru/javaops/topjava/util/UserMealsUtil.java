package ru.javaops.topjava.util;

import ru.javaops.topjava.model.UserMeal;
import ru.javaops.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserMealsUtil {

    private static final int CALORIES_PER_DAY = 2000;
    private static final LocalTime startTime = LocalTime.of(7, 0);
    private static final LocalTime endTime = LocalTime.of(12, 0);

    private static final List<UserMeal> meals = Arrays.asList(
            new UserMeal(LocalDateTime.of(2025, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2025, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new UserMeal(LocalDateTime.of(2025, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2025, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new UserMeal(LocalDateTime.of(2025, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new UserMeal(LocalDateTime.of(2025, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2025, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    public static void main(String[] args) {
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, startTime, endTime, CALORIES_PER_DAY);
        mealsTo.forEach(System.out::println); // NOSONAR

        mealsTo = filteredByStreams(meals, startTime, endTime, CALORIES_PER_DAY);
        mealsTo.forEach(System.out::println); // NOSONAR
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        var caloriesPerDays = new HashMap<LocalDate, Integer>();
        meals.forEach(meal -> caloriesPerDays.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum));

        var mealsTo = new ArrayList<UserMealWithExcess>();
        for (UserMeal meal : meals) {
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean excess = caloriesPerDays.get(meal.getDateTime().toLocalDate()) > caloriesPerDay;
                mealsTo.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess));
            }
        }
        return mealsTo;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) { // NOSONAR
        return null; // NOSONAR
    }
}
