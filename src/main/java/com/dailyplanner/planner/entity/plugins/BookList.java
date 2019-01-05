package com.dailyplanner.planner.entity.plugins;

import com.dailyplanner.planner.entity.plugins.GeneralList;

import java.util.ArrayList;

public class BookList extends GeneralList {
    protected String type = "Book";
    protected ArrayList<String> items = new ArrayList<>();
}
