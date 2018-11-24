package com.dailyplanner.planner.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class UserAccount {
    private User user;
    private LinkedHashSet<PlannerDay> plannerDays;
    private ArrayList<GeneralList> lists;
    private ArrayList<GeneralPlugin> plugins;
}
