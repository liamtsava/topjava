package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * Klindziuk
 * 11.03.2018.
 */
public class UserMeal {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMeal userMeal = (UserMeal) o;

        if (calories != userMeal.calories) return false;
        if (dateTime != null ? !dateTime.equals(userMeal.dateTime) : userMeal.dateTime != null) return false;
        return description != null ? description.equals(userMeal.description) : userMeal.description == null;

    }

    @Override
    public int hashCode() {
        int result = dateTime != null ? dateTime.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + calories;
        return result;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
