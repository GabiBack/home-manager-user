package com.example.homemanageruser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @PostMapping("/user-name-test/{name}")
    public void registerUser(@PathVariable String name){

    }
}
