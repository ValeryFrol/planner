package com.dailyplanner.planner.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class UserAccount {
    private User user;
    private LinkedHashSet<PlannerDay> plannerDays;
    private ArrayList<GeneralList> lists;
    private ArrayList<GeneralPlugin> plugins;
    private ArrayList<User> friends;

    public UserAccount(User user) {
        this.user = user;
        for(int i=0;i<366;i++){
            plannerDays.add(new PlannerDay(LocalDate.now().plusDays(i)));
        }
    }

    public User getUser() {
        return user;
    }

    public LinkedHashSet<PlannerDay> getPlannerDays() {
        return (LinkedHashSet<PlannerDay>)plannerDays.clone();
    }

    public ArrayList<GeneralList> getLists() {
        return (ArrayList<GeneralList>)lists.clone();
    }

    public ArrayList<GeneralPlugin> getPlugins() {
        return (ArrayList<GeneralPlugin>)plugins.clone();
    }

    public ArrayList<User> getFriends() {
        return (ArrayList<User>)friends.clone();
    }
}
