package com.example.homemanageruser.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;
    private String groupName;
    private String password;
    private String email;
}
