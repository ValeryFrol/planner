package com.dailyplanner.planner.dao;

import java.util.List;

public interface DaoInterface<T> {
    public void create(T t);
    public void update(T t);
    public void delete(int id);
    public List<T> getAll();
    public T getById(int id);



}
