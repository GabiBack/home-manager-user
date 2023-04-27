package com.example.homemanageruser.event;

import com.example.homemanageruser.defaults.AuthenticationDefaults;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRegistrationCompleteEventListener implements ApplicationListener<UserRegistrationCompleteEvent> {

    Logger LOGGER = LoggerFactory.getLogger(UserRegistrationCompleteEventListener.class);

    @Autowired
    private AuthenticationService service;

    @Override
    public void onApplicationEvent(UserRegistrationCompleteEvent event) {
        UserEntity userEntity = event.getUserEntity();
        String token = UUID.randomUUID().toString();

        service.saveVerificationTokenForUser(token,userEntity);
        this.sendConfirmationEmail(event,token);
    }

    //TODO Implement sending mail by gmail
    private void sendConfirmationEmail(UserRegistrationCompleteEvent event, String token) {
        String url = event.getApplicationUrl() + AuthenticationDefaults.TOKEN_VERIFICATION_URL + token;
        LOGGER.info("Click the link to verify your account: {}", url);
    }
}
