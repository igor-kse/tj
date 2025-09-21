package ru.javaops.topjava.mapper;

import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.dto.MealTo;

public class MealMapper {

    private MealMapper() {
    }

    public static MealTo map(Meal meal, boolean excess) {
        return new MealTo(meal.dateTime(), meal.description(), meal.calories(), excess);
    }
}
