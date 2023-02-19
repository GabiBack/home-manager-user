package com.example.homemanageruser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user/test")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/{name}")
    public void registerUser(@PathVariable String name){
        User user = new User(name);
        this.userRepository.save(user);
    }
}
