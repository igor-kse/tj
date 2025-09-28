package ru.javaops.topjava.to;

import java.time.LocalDateTime;

public record MealTo(long id, LocalDateTime dateTime, String description, int calories, boolean excess) {
}
