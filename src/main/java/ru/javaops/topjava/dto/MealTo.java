package ru.javaops.topjava.dto;

import java.time.LocalDateTime;

public record MealTo(int id, LocalDateTime dateTime, String description, int calories, boolean excess) {
}
