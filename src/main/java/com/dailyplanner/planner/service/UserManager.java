package com.dailyplanner.planner.service;

import com.dailyplanner.planner.dao.UserDao;
import com.dailyplanner.planner.entity.User;

public class UserManager {
    private UserDao userDao;
    public boolean removeUser(User user){
        //todo call method from UserDao for removal
        //todo if DB doesn't contain this user anymore return true, otherwise return false
        //todo handle an exception when such user doesn't exist
        return true;
    }
    public User changeUser(User user){
        //todo call method from UserDao for changing
        //todo if DB doesn't contain this user anymore return true, otherwise return false
        //todo handle an exception when such user doesn't exist and if it can't be changed
        return user;
    }
    public boolean addUser(User user){
        //todo call method from UserDao for adding user
        //todo handle an exception if smth goes wrong
        return true;
    }
    /*public User getUserById(int id){

        return user;
    }*/

}
