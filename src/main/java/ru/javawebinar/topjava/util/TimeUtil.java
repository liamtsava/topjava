package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Klindziuk
 * 11.03.2018.
 */
public final class TimeUtil {

    private TimeUtil() {
    }

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetween(LocalDate localDate, LocalDate currentLocalDate) {
        return localDate.equals(currentLocalDate);
    }

}
