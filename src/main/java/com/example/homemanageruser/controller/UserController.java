package com.example.homemanageruser.controller;

import com.example.homemanageruser.exception.DataNotColpeteException;
import com.example.homemanageruser.exception.UserAlreadyExistsException;
import com.example.homemanageruser.model.authentication.AuthenticationResponse;
import com.example.homemanageruser.model.user.RegisterRequest;
import com.example.homemanageruser.service.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    UserService service;

//    //TODO Swagger
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> registerUser(
//            @RequestBody RegisterRequest request) throws UserAlreadyExistsException, DataNotColpeteException {
//
//        return ResponseEntity.ok(service.register(request));
//    }

    //TODO zapros innych userow do swojej grupy

    @GetMapping("/greeting")
    public String getGreeting(){
        return "Welcome to our home manager app";
    }
}
