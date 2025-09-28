package ru.javaops.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public final class Meal extends BaseEntity{
    private LocalDateTime dateTime;
    private String description;
    private int calories;
    private Long userId;

    public Meal(LocalDateTime dateTime, String description, int calories, Long userId) {
        super(null);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
    }

    public Meal(Long id, LocalDateTime dateTime, String description, int calories, Long userId) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userId = userId;
    }

    public Meal(Meal meal) {
        super(meal.getId());
        this.dateTime = meal.getDateTime();
        this.description = meal.getDescription();
        this.calories = meal.getCalories();
        this.userId = meal.getUserId();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Meal) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.dateTime, that.dateTime) &&
                Objects.equals(this.description, that.description) &&
                this.calories == that.calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, description, calories);
    }

    @Override
    public String toString() {
        return "Meal[" +
                "id=" + id + ", " +
                "dateTime=" + dateTime + ", " +
                "description=" + description + ", " +
                "calories=" + calories + ']';
    }
}
