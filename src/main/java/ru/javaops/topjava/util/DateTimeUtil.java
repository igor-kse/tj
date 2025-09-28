package ru.javaops.topjava.util;

import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static boolean isBetweenHalfOpen(LocalTime lt, @Nullable LocalTime startTime, @Nullable LocalTime endTime) {
        if (lt == null) {
            return false;
        }
        if (startTime != null && lt.isBefore(startTime)) {
            return false;
        }
        return endTime == null || lt.isBefore(endTime);
    }

    public static LocalDateTime atStartOfDayOrMin(@Nullable LocalDate date) {
        return date != null ? date.atStartOfDay() : LocalDateTime.MIN;
    }

    public static LocalDateTime atStartOfNextDayOrMax(@Nullable LocalDate date) {
        return date != null ? date.plusDays(1).atStartOfDay() : LocalDateTime.MAX;
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
