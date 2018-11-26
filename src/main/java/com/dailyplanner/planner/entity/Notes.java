package com.dailyplanner.planner.entity;

import java.time.LocalDate;

public class Notes {
    private String text;
    private LocalDate date;

    public Notes(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }
}
