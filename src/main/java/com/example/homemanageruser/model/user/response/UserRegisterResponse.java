package com.example.homemanageruser.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
@AllArgsConstructor
public class UserRegisterResponse {

    String verificationUrl;
}
