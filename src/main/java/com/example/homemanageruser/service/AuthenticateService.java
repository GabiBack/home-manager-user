package com.example.homemanageruser.service;

import com.example.homemanageruser.model.UserEntity;
import com.example.homemanageruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticateService {

    @Autowired
    UserRepository userRepository;

    public UserDetails findUserDetailsByEmail(String email){
        List<UserEntity> users = this.userRepository.findAll();
        User user = null;

        UserEntity userEntity =  users.stream()
                .filter(e -> e.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        //.orElseThrow(() -> new UsernameNotFoundException("User " + userName + " was not found"));

        if (userEntity != null){
            user = new User(
                    userEntity.getEmail(),
                    userEntity.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        }

        return user;
    }
}
