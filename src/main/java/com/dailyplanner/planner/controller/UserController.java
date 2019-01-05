package com.dailyplanner.planner.controller;


import com.dailyplanner.planner.dao.UserDao;
import com.dailyplanner.planner.entity.User;
import com.dailyplanner.planner.service.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(value="/username",method= RequestMethod.GET)
    public List<String> getAllUserNames(){
        return userManager.getAllUserNames();
    }

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){
        userManager.addUser(user);
    }

    @RequestMapping(value="/users",method= RequestMethod.GET)
    public List<User> getAllUsers(){
        return userManager.getAllUsers();
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void changeUser(@RequestBody User user){
        userManager.changeUser(user);
    }


}
