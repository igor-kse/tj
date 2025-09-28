package ru.javaops.topjava.repository;

import ru.javaops.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal, long ownerId);

    Meal find(long id, long ownerId);

    boolean delete(long id, long ownerId);

    List<Meal> getAll(long ownerId);

    List<Meal> getBetweenHalfOpen(long ownerId, LocalDateTime start, LocalDateTime end);
}
