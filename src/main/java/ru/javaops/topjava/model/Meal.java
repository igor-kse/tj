package ru.javaops.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record Meal(LocalDateTime dateTime, String description, int calories) {

    public LocalDate date() {
        return dateTime.toLocalDate();
    }

    public LocalTime time() {
        return dateTime.toLocalTime();
    }
}
