package ru.javaops.topjava.mapper;

import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.to.MealTo;
import ru.javaops.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

public class MealMapper {

    private MealMapper() {
    }

    public static MealTo map(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
