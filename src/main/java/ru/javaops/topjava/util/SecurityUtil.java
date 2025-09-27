package ru.javaops.topjava.util;

public class SecurityUtil {

    // FIXME for development only
    private static final int DEFAULT_CALORIES_PER_DAY = 2000;

    // FIXME For development only
    private static final int TEMPORARY_USER_ID = 1;

    private SecurityUtil() {}

    public static Integer authUserId() {
        return TEMPORARY_USER_ID;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}
