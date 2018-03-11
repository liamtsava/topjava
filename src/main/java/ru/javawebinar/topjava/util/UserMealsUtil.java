package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Klindziuk
 * 11.03.2018.
 */
public final class UserMealsUtil {

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalDate localDate, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> exceededList = getFilteredWithExceeded(mealList, localDate, caloriesPerDay);
        return getDailyTimeMealList(exceededList, startTime, endTime);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Set<LocalDate> dateSet = getDatesFromMealList(mealList);
        List<UserMealWithExceed> fullExceedMealList = getFullExceedMealList(dateSet, mealList, caloriesPerDay);
        return getDailyTimeMealList(fullExceedMealList, startTime, endTime);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalDate localDate, int caloriesPerDay) {
        List<UserMealWithExceed> exceedList = getExceedFromUserMeal(mealList);
        List<UserMealWithExceed> dailyList = getDailyMealList(exceedList, localDate);
        return getUserMealExceededList(dailyList, caloriesPerDay);
    }

    private static List<UserMealWithExceed> getFullExceedMealList(Set<LocalDate> dateSet, List<UserMeal> mealList, int caloriesPerDay) {
        List<UserMealWithExceed> result = new ArrayList<>();
        for (LocalDate localDate : dateSet) {
            List<UserMealWithExceed> dailyList = getFilteredWithExceeded(mealList, localDate, caloriesPerDay);
            result.addAll(dailyList);
        }
        return result;
    }

    private static Set<LocalDate> getDatesFromMealList(List<UserMeal> mealList) {
        Set<LocalDate> dateSet = mealList.stream().map(userMeal -> userMeal.getDateTime().toLocalDate())
                .collect(Collectors.toSet());
        return dateSet;
    }

    private static List<UserMealWithExceed> getDailyTimeMealList(List<UserMealWithExceed> mealList, LocalTime startTime, LocalTime endTime) {
        List<UserMealWithExceed> dailyList = mealList.stream().filter(userMealWithExceed ->
                TimeUtil.isBetween(userMealWithExceed.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
        return dailyList;
    }

    private static List<UserMealWithExceed> getDailyMealList(List<UserMealWithExceed> mealList, LocalDate localDate) {
        List<UserMealWithExceed> dailyList = mealList.stream().filter(userMealWithExceed ->
                TimeUtil.isBetween(userMealWithExceed.getDateTime().toLocalDate(), localDate))
                .collect(Collectors.toList());
        return dailyList;
    }

    private static List<UserMealWithExceed> getUserMealExceededList(List<UserMealWithExceed> dailyMealList, int caloriesPerDay) {
        int dailyCalories = getCalories(dailyMealList);
        if (dailyCalories > caloriesPerDay) {
            getChangedList(dailyMealList);
        }
        return dailyMealList;
    }

    private static List<UserMealWithExceed> getChangedList(List<UserMealWithExceed> userMealExceededList) {
        for (UserMealWithExceed userMealWithExceed : userMealExceededList) {
            userMealWithExceed.setExceed(true);
        }
        return userMealExceededList;
    }

    private static List<UserMealWithExceed> getExceedFromUserMeal(List<UserMeal> dailyMealList) {
        List<UserMealWithExceed> result = dailyMealList.stream().map(userMeal ->
                new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false))
                .collect(Collectors.toList());
        return result;
    }

    private static int getCalories(List<UserMealWithExceed> dailyMeal) {
        int result = dailyMeal.stream().mapToInt(UserMealWithExceed::getCalories).sum();
        return result;
    }
}
