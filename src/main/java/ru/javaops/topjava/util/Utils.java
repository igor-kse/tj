package ru.javaops.topjava.util;

public class Utils {

    private Utils() {}

    public static void assureNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
