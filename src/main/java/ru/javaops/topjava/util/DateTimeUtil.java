package ru.javaops.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return !lt.isBefore(startTime) && lt.isBefore(endTime);
    }

    public static String format(Object temporal, String format) {
        if (temporal == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return switch (temporal) {
            case LocalDateTime ldc -> formatter.format(ldc);
            case LocalDate ld -> formatter.format(ld);
            case LocalTime lt -> formatter.format(lt);
            default -> throw new IllegalArgumentException("The type is not supported: " + temporal.getClass().getName());
        };
    }
}
