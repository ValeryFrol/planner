package com.dailyplanner.planner.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    private String text;
    private LocalDate date;
    private LocalTime time;
    private boolean status;
    private ArrayList<User> userWithAccess;

    public Event(String text, LocalDate date, LocalTime time) {
        this.text = text;
        this.date = date;
        this.time = time;
        this.status = false;
    }

    public Event(String text, LocalDate date, LocalTime time, ArrayList<User> userWithAccess) {
        this.text = text;
        this.date = date;
        this.time = time;
        this.status = false;
        this.userWithAccess = userWithAccess;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public ArrayList<User> getUserWithAccess() {
        return userWithAccess;
    }
}
