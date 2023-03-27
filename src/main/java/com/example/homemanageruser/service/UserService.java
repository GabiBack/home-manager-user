package com.example.homemanageruser.service;

import com.example.homemanageruser.config.JwtService;
import com.example.homemanageruser.model.authentication.AuthenticationResponse;
import com.example.homemanageruser.model.authentication.Role;
import com.example.homemanageruser.model.user.RegisterRequest;
import com.example.homemanageruser.model.user.User;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        //TODO Sprawdz czy juz nie ma takiego usera w tej grupie
        //TODO Walidacja czy nie brakuje jakichs pol
        UserEntity userEntity = UserEntity.builder()
                .setUserName(request.getUserName())
                .setGroupName(request.getGroupName())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setEmail(request.getEmail())
                .setRole(Role.USER)
                .build();

        repository.save(userEntity);
        String jwtToken = jwtService.generateToken(userEntity);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

//    private UserMapper userMapper;
//
//    public User registerUser(String username,
//                             String groupName,
//                             String password,
//                             String email,
//                             Boolean isAdmin){
//
//
//        UserEntity userEntity = this.findUserEntityByUserNameAndGroupName(username, groupName);
//
//        return this.userMapper.userEntityToUser(userEntity);
//    }
//
//    public UserEntity findUserEntityByUserNameAndGroupName(String userName, String groupName){
//        List<UserEntity> users = this.userRepository.findAll();
//
//        return users.stream()
//                .filter(g -> g.getGroupName().equals(groupName))
//                .filter(u -> u.getUserName().equals(userName))
//                .findFirst()
//                .orElse(null);
//        //.orElseThrow(() -> new UsernameNotFoundException("User " + userName + " was not found"));

//    }
}
