package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    void save(Meal meal);

    Meal findById(int id);

    void deleteById(int id);

    boolean update(Meal meal);

    List<Meal> getAll();
}
