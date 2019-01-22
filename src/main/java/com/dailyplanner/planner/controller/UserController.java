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

   /* @RequestMapping(value="/username",method= RequestMethod.GET)
    public List<String> getAllUserNames(){
        return userManager.getAllUserNames();
    }*/

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody User user){
        userManager.create(user);
    }

    @RequestMapping(value="/users",method= RequestMethod.GET)
    public List<User> getAll(){
        return userManager.getAll();
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user){
        userManager.update(user);
    }

    @RequestMapping(value="/user/{userId}/delete",method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("userId") int id){
        userManager.delete(id);
    }

    @RequestMapping(value="/user/{userId}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable("userId") int id){
        return userManager.getById(id);
    }


}
