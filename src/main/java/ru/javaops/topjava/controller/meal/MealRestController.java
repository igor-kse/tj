package ru.javaops.topjava.controller.meal;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javaops.topjava.model.Meal;
import ru.javaops.topjava.service.MealService;
import ru.javaops.topjava.to.MealTo;
import ru.javaops.topjava.util.MealsUtil;
import ru.javaops.topjava.util.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javaops.topjava.util.SecurityUtil.authUserCaloriesPerDay;
import static ru.javaops.topjava.util.SecurityUtil.authUserId;
import static ru.javaops.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava.util.ValidationUtil.assureNotNull;

@Controller
public class MealRestController {
    private static final String MEAL_NOT_NULL_MESSAGE = "Meal cannot be null";

    private final MealService mealService;

    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal get(long id) {
        return mealService.get(id, authUserId());
    }

    public void save(Meal meal) {
        assureNotNull(meal, MEAL_NOT_NULL_MESSAGE);
        mealService.save(meal, authUserId());
    }

    public void update(Meal meal, long id) {
        assureNotNull(meal, MEAL_NOT_NULL_MESSAGE);
        assureIdConsistent(meal, id);
        mealService.update(meal, authUserId());
    }

    public void delete(long id) {
        mealService.delete(id, authUserId());
    }

    public List<MealTo> getAll() {
        List<Meal> meals = mealService.getAllMeals(authUserId());
        return MealsUtil.getTos(meals, authUserCaloriesPerDay());
    }

    public List<MealTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                   @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        long userId = authUserId();
        List<Meal> mealsDateFiltered = mealService.getBetweenInclusive(startDate, endDate, userId);

        int userCaloriesPerDay = SecurityUtil.authUserCaloriesPerDay();
        return MealsUtil.getFilteredTos(mealsDateFiltered, userCaloriesPerDay, startTime, endTime);
    }
}
