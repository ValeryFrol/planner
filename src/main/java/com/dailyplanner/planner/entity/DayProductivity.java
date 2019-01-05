package com.dailyplanner.planner.entity;

import java.time.LocalDate;

public class DayProductivity {
    private LocalDate date;
    private int stars;

    public DayProductivity(int stars, LocalDate date) {
        this.stars = stars;
        this.date = date;
    }

    public int getStars() {
        return stars;
    }
}
