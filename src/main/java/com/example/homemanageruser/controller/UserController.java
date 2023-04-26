package com.example.homemanageruser.controller;

import com.example.homemanageruser.event.UserRegistrationCompleteEvent;
import com.example.homemanageruser.model.user.User;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.service.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    private ApplicationEventPublisher publisher;

//    //TODO Swagger
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> registerUser(
//            @RequestBody RegisterRequest request) throws UserAlreadyExistsException, DataNotColpeteException {
//
//        return ResponseEntity.ok(service.register(request));
//    }

    //TODO Zaimplementowac RegisterResponse: return ResponseEntity.ok(service.register(request));
    @PostMapping("/register")
    public String userRegister(@RequestBody User user, final HttpServletRequest request){
        UserEntity userEntity = service.register(user);
        publisher.publishEvent(
                new UserRegistrationCompleteEvent(
                        userEntity,
                        service.applicationUrl(request)));
        return "Success";
    }

    //TODO zapros innych userow do swojej grupy

    @GetMapping("/greeting")
    public String getGreeting(){
        return "Welcome to our home manager app";
    }
}
