package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.util.UserMealsUtil.getFilteredWithExceeded;

/**
 * User: klindziuk
 * Date: 11.03.2018
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        System.out.println("Filtered by Time");
        System.out.println("========================================================");
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        System.out.println("========================================================" +"\n");
        System.out.println("Filtered by Date");
        System.out.println("========================================================");
        System.out.println(getFilteredWithExceeded(mealList, LocalDate.of(2015, Month.MAY, 30), 2000));
        System.out.println("========================================================" +"\n");
        System.out.println("Filtered by Date and Time");
        System.out.println("========================================================");
        System.out.println(getFilteredWithExceeded(mealList, LocalDate.of(2015, Month.MAY, 31), LocalTime.of(7, 0), LocalTime.of(14, 0), 2000));
        System.out.println("========================================================" +"\n");
    }
}
