package com.example.homemanageruser.controller;

import com.example.homemanageruser.event.UserRegistrationCompleteEvent;
import com.example.homemanageruser.exception.DataNotColpeteException;
import com.example.homemanageruser.exception.UserAlreadyExistsException;
import com.example.homemanageruser.model.user.User;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.model.user.request.UserLoginRequest;
import com.example.homemanageruser.model.user.response.UserLoginResponse;
import com.example.homemanageruser.model.user.response.UserRegisterResponse;
import com.example.homemanageruser.service.AuthenticationService;
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
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private ApplicationEventPublisher publisher;

    //TODO Swagger
    @GetMapping("/home")
    public String getGreeting(){
        return "Welcome to our home manager app";
    }

    //TODO Zaimplementowac RegisterResponse: return ResponseEntity.ok(service.register(request));

    @PostMapping("/register")
    public UserRegisterResponse userRegister(@RequestBody User user,
                                             final HttpServletRequest request) throws DataNotColpeteException, UserAlreadyExistsException {

        userService.authenticateRegistrationData(user);
        UserEntity userEntity = userService.register(user);
        String authenticationUrl =  authenticationService.applicationUrl(request);

        publisher.publishEvent(
                new UserRegistrationCompleteEvent(
                        userEntity,
                        authenticationUrl
                       ));

        return new UserRegisterResponse("Verification url is in backend logs");
    }

    @PostMapping("/login")
    public UserLoginResponse loginUser(@RequestBody UserLoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    //TODO zapros innych userow do swojej grupy

}
