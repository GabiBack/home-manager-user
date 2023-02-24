package com.example.homemanageruser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/user/test")
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/{name}")
    public void registerUser(@PathVariable String name){
        UserEntity user = UserEntity.builder()
                .name(name)
                .build();

        this.userRepository.save(user);
        log.info("Saved new customer with name: " + name);
    }

    @GetMapping("/greeting")
    public String getGreeting(){
        return "Welcome to our home manager app";
    }
}
