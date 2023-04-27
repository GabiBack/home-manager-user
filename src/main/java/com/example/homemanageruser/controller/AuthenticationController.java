package com.example.homemanageruser.controller;

import com.example.homemanageruser.message.UserMessageProducer;
import com.example.homemanageruser.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserMessageProducer messageProducer ;

    private final AuthenticationService service;

    Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/verify-registration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = service.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return "Users verified - success";
        } else {
            return "User cannot be verified";
        }
    }

    @PostMapping("/{userId}")
    public void sendUserCredentials(@PathVariable Long userId) {
        messageProducer .sendMessage(userId.toString());
        LOGGER.info("Message with authenticated userId sent to Kafka");
    }
}
