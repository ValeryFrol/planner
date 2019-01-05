package com.dailyplanner.planner.service;

import com.dailyplanner.planner.dao.UserDao;
import com.dailyplanner.planner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {
    @Autowired
    private UserDao userDao;
    public List<String> getAllUserNames(){
        return userDao.getAllUserNames();
    }
    public boolean removeUser(User user){
        //todo call method from UserDao for removal
        //todo if DB doesn't contain this user anymore return true, otherwise return false
        //todo handle an exception when such user doesn't exist
        return true;
    }
    public void changeUser(User user){
        userDao.changeUser(user);
        //todo call method from UserDao for changing
        //todo if DB doesn't contain this user anymore return true, otherwise return false
        //todo handle an exception when such user doesn't exist and if it can't be changed

    }
    public void addUser(User user){
        userDao.addUser(user);
      }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

       /*public User getUserById(int id){

        return user;
    }*/

}
