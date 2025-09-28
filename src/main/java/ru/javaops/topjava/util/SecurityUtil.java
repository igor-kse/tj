package ru.javaops.topjava.util;

public class SecurityUtil {

    private static final long TEMPORARY_USER_ID = 0;

    private SecurityUtil() {}

    public static Long authUserId() {
        return TEMPORARY_USER_ID;
    }

    public static int authUserCaloriesPerDay() {
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
