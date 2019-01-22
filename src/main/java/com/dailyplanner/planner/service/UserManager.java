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

    /* public List<String> getAllUserNames(){
         return userDao.getAllUserNames();
     }*/
    public void delete(int id) {
        userDao.delete(id);
    }

    public void update(User user) {
        userDao.update(user);
        //todo call method from UserDao for changing
        //todo if DB doesn't contain this user anymore return true, otherwise return false
        //todo handle an exception when such user doesn't exist and if it can't be changed

    }

    public void create(User user) {
        userDao.create(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int id) {

        return userDao.getById(id);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }
    public User findByNickname(String nickname){
        return userDao.findByNickname(nickname);
    }

}
