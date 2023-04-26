package com.example.homemanageruser.event;


import com.example.homemanageruser.model.user.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserRegistrationCompleteEvent extends ApplicationEvent {

    private UserEntity userEntity;
    private String applicationUrl;

    public UserRegistrationCompleteEvent(UserEntity userEntity, String applicationUrl) {
        super(userEntity);
        this.userEntity = userEntity;
        this.applicationUrl = applicationUrl;
    }

}
