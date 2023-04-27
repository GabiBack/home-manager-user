package com.example.homemanageruser.model.user.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserLoginRequest {

    private String email;
    private String password;
}
