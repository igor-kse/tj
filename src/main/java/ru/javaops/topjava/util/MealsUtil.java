package ru.javaops.topjava.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javaops.topjava.mapper.MealMapper;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.dto.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MealsUtil {

    public static final int CALORIES_PER_DAY = 2000;

    public static final LocalTime START_TIME = LocalTime.of(7, 0);
    public static final LocalTime END_TIME = LocalTime.of(12, 0);

    public static final List<Meal> meals = List.of(
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    private static final Logger log = LoggerFactory.getLogger(MealsUtil.class);

    private MealsUtil() {
    }

    public static void main(String[] args) {
        List<MealTo> mealsTo = filteredByCycles(meals, START_TIME, END_TIME, CALORIES_PER_DAY);
        log.info("Cycle implementation: {}", mealsTo);

        mealsTo = filteredByStreams(meals, START_TIME, END_TIME, CALORIES_PER_DAY);
        log.info("Stream API implementation: {}", mealsTo);
    }

    public static List<MealTo> filteredByCycles(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        var caloriesPerDays = new HashMap<LocalDate, Integer>();
        meals.forEach(meal -> caloriesPerDays.merge(meal.dateTime().toLocalDate(), meal.calories(), Integer::sum));

        var mealsTo = new ArrayList<MealTo>();
        for (Meal meal : meals) {
            if (TimeUtil.isBetweenHalfOpen(meal.dateTime().toLocalTime(), startTime, endTime)) {
                boolean excess = caloriesPerDays.get(meal.dateTime().toLocalDate()) > caloriesPerDay;
                mealsTo.add(new MealTo(meal.dateTime(), meal.description(), meal.calories(), excess));
            }
        }
        return mealsTo;
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        var caloriesPerDays = meals.stream()
                .collect(Collectors.groupingBy(Meal::date, Collectors.summingInt(Meal::calories)));

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.time(), startTime, endTime))
                .map(meal -> MealMapper.map(meal, caloriesPerDays.get(meal.date()) > caloriesPerDay))
                .toList();
    }
}
