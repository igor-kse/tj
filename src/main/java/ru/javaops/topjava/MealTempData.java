package ru.javaops.topjava;

import ru.javaops.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javaops.topjava.util.SecurityUtil.authUserId;

public class MealTempData {

    private MealTempData() {}

    public static final List<Meal> meals = List.of(
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 30, 10, 0), "Завтрак", 500, authUserId()),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 30, 13, 0), "Обед", 1000, authUserId()),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 30, 20, 0), "Ужин", 500, authUserId()),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, authUserId()),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, authUserId()),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 13, 0), "Обед", 500, authUserId()),
            new Meal(LocalDateTime.of(2025, Month.JANUARY, 31, 20, 0), "Ужин", 410, authUserId())
    );
}
