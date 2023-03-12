package com.example.homemanageruser.controller;

import com.example.homemanageruser.model.User;
import com.example.homemanageruser.service.UserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController implements HomeManagerUserController {

    Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    //TODO Swagger
    @PostMapping("/register")
    public User registerUser(@RequestParam String username,
                             @RequestParam String groupName,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam Boolean isAdmin){


        User user = this.userService.registerUser(username,groupName,password,email,isAdmin);
        LOGGER.info("Saved new customer with name: " + username + " to the group " + groupName);

        return user;

    }

    //TODO zapros innych userow do swojej grupy

    @GetMapping("/greeting")
    public String getGreeting(){
        return "Welcome to our home manager app";
    }
}
