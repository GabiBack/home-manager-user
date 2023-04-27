package com.example.homemanageruser.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserLoginResponse {

    private HttpStatus httpStatus;
    private Boolean userAuthenticated;
    private Long userId;

    public UserLoginResponse(HttpStatus httpStatus, Boolean userAuthenticated) {
        this.httpStatus = httpStatus;
        this.userAuthenticated = userAuthenticated;
    }
}
