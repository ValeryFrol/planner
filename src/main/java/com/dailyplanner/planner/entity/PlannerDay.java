package com.dailyplanner.planner.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class PlannerDay {
    private ArrayList<Event> eventList;
    private ArrayList<Notes> notesList;
    private DayProductivity dayProductivity;
    private LocalDate date;

    public PlannerDay(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlannerDay)) return false;
        PlannerDay that = (PlannerDay) o;
        return this.eventList.equals(that.eventList) &&
                this.notesList.equals(that.notesList) &&
                this.dayProductivity.equals(that.dayProductivity) &&
                this.date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.date == null ? 0 : this.date.hashCode());
        return hash;
    }

    public ArrayList<Event> getEventList() {
        return (ArrayList<Event>)eventList.clone();
    }

    public ArrayList<Notes> getNotesList() {
        return (ArrayList<Notes>)notesList.clone();
    }

    public DayProductivity getDayProductivity() {
        return dayProductivity;
    }

    public LocalDate getDate() {
        return date;
    }
}
