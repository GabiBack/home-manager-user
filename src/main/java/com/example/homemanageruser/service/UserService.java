package com.example.homemanageruser.service;

import com.example.homemanageruser.exception.DataNotColpeteException;
import com.example.homemanageruser.exception.UserAlreadyExistsException;
//import com.example.homemanageruser.config.JwtService;
import com.example.homemanageruser.model.authentication.AuthenticationResponse;
import com.example.homemanageruser.model.authentication.Role;
import com.example.homemanageruser.model.user.RegisterRequest;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;

    Logger LOGGER = LoggerFactory.getLogger(UserService.class);

//    public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExistsException, DataNotColpeteException {
//        this.authenticateRegistrationData(request);
//
//        UserEntity userEntity = UserEntity.builder()
//                .setUserName(request.getUserName())
//                .setGroupName(request.getGroupName())
//                .setPassword(passwordEncoder.encode(request.getPassword()))
//                .setEmail(request.getEmail())
//                .setRole(Role.USER)
//                .build();
//
//        repository.save(userEntity);
//        String jwtToken = jwtService.generateToken(userEntity);
//
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

    private void authenticateRegistrationData(RegisterRequest request) throws UserAlreadyExistsException, DataNotColpeteException {
        if(this.userAlreadyExists(request.getEmail())){
            LOGGER.error("Cannot register new user because email is already in use");
            throw new UserAlreadyExistsException("User with this email already exists");
        } else if (this.userAlreadyExistsInTheGroup(request.getUserName(), request.getGroupName())){
            LOGGER.error("Cannot register new user because user name is already taken by other group member");
            throw new UserAlreadyExistsException("User with this name already exists in the group");
        }
        if(!this.dataAreComplete(request)){
            LOGGER.error("Cannot register new user because data are not complete");
            throw new DataNotColpeteException("Data are not complete");
        }
    }

    private Boolean userAlreadyExists(String email){
        UserEntity userEntity = repository.findByEmail(email).orElse(null);
        return userEntity != null;
    }

    private Boolean userAlreadyExistsInTheGroup(String userName, String groupName){
        UserEntity userEntity = repository.findUserInGroupByUserName(userName, groupName);
        return userEntity != null;
    }

    private Boolean dataAreComplete(RegisterRequest request) {
        return request.getEmail() != null &&
                request.getUserName() != null &&
                request.getGroupName() != null &&
                request.getPassword() != null;
    }

}
