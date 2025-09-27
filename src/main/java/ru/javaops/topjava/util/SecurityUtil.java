package ru.javaops.topjava.util;

public class SecurityUtil {

    // FIXME For development only
    private static final int TEMPORARY_USER_ID = 1;

    private SecurityUtil() {}

    public static Integer authUserId() {
        return TEMPORARY_USER_ID;
    }

    public static int authUserCaloriesPerDay() {
        return MealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
