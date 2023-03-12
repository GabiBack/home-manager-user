package com.example.homemanageruser.service;

import com.example.homemanageruser.model.User;
import com.example.homemanageruser.model.UserEntity;
import com.example.homemanageruser.model.UserMapper;
import com.example.homemanageruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User registerUser(String username,
                             String groupName,
                             String password,
                             String email,
                             Boolean isAdmin){

        //TODO Sprawdz czy juz nie ma takiego usera w tej grupie
        //TODO Walidacja czy nie brakuje jakichs pol
        //TODO Jak sie zapisuje haslo

        this.userRepository.save(UserEntity.builder()
                .setUserName(username)
                .setGroupName(groupName)
                .setPassword(password)
                .setEmail(email)
                .isAdmin(isAdmin)
                .build());

        UserEntity userEntity = this.findUserEntityByUserNameAndGroupName(username, groupName);

        return this.userMapper.userEntityToUser(userEntity);
    }

    public UserEntity findUserEntityByUserNameAndGroupName(String userName, String groupName){
        List<UserEntity> users = this.userRepository.findAll();

        return users.stream()
                .filter(g -> g.getGroupName().equals(groupName))
                .filter(u -> u.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
        //.orElseThrow(() -> new UsernameNotFoundException("User " + userName + " was not found"));
    }

}
