package com.example.homemanageruser.service;

import com.example.homemanageruser.defaults.UserDefaults;
import com.example.homemanageruser.exception.DataNotColpeteException;
import com.example.homemanageruser.exception.UserAlreadyExistsException;
import com.example.homemanageruser.model.authentication.Role;
import com.example.homemanageruser.model.user.User;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.model.user.request.UserLoginRequest;
import com.example.homemanageruser.model.user.response.UserLoginResponse;
import com.example.homemanageruser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    //TODO zaimplementowac mapper z User na UserEntity
    public UserEntity register(User user){
        UserEntity userEntity =  UserEntity.builder()
                .setUserName(user.getUserName())
                .setGroupName(user.getGroupName())
                .setEmail(user.getEmail())
                .setRole(Role.USER)
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .build();

        repository.save(userEntity);
        return userEntity;

    }

    public void authenticateRegistrationData(User user) throws UserAlreadyExistsException, DataNotColpeteException {
        if(this.userAlreadyExists(user.getEmail())){
            LOGGER.error("Cannot register new user because email is already in use");
            throw new UserAlreadyExistsException("User with this email already exists");
        } else if (this.userAlreadyExistsInTheGroup(user.getUserName(), user.getGroupName())){
            LOGGER.error("Cannot register new user because user name is already taken by other group member");
            throw new UserAlreadyExistsException("User with this name already exists in the group");
        }
        if(!this.dataAreComplete(user)){
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

    private Boolean dataAreComplete(User user) {
        return user.getEmail() != null &&
                user.getUserName() != null &&
                user.getGroupName() != null &&
                user.getPassword() != null;
    }


    public UserLoginResponse login(UserLoginRequest loginRequest) {
        String userEmail = loginRequest.getEmail();
        String userPassword =loginRequest.getPassword();
        Optional<UserEntity> userEntity = repository.findByEmail(userEmail);

        return userEntity.map(entity -> this.userAuthenticate(entity, userPassword))
                .orElseGet(() -> this.userNotFound(userEmail));
    }

    private UserLoginResponse userAuthenticate(UserEntity userEntity, String passwordToAuthenticate) {
        if(userIsDisabled(userEntity)){
            return new UserLoginResponse(HttpStatus.FORBIDDEN, UserDefaults.AUTHENTICATION_FAILED);
        } else {
            return passwordAuthentication(userEntity,passwordToAuthenticate);
        }
    }

    private Boolean userIsDisabled(UserEntity userEntity){
        if(!userEntity.isEnabled()){
            LOGGER.warn("User " + userEntity.getId() + "is not active");
            return true;
        } else {
            return false;
        }
    }

    private UserLoginResponse passwordAuthentication(UserEntity userEntity, String passwordToAuthenticate) {
        boolean passwordIsCorrect = this.passwordEncoder.matches(passwordToAuthenticate, userEntity.getPassword());
        if (passwordIsCorrect) {
            return new UserLoginResponse(HttpStatus.OK, UserDefaults.AUTHENTICATION_SUCCESS, userEntity.getId());
        } else {
            LOGGER.warn("User " + userEntity.getId() + "cannot be authenticated due to incorrect password");
            return new UserLoginResponse(HttpStatus.FORBIDDEN, UserDefaults.AUTHENTICATION_FAILED);
        }
    }

    private UserLoginResponse userNotFound(String userEmail) {
        LOGGER.error("User with email" + userEmail + "cannot be found");
        return new UserLoginResponse(HttpStatus.NOT_FOUND, UserDefaults.AUTHENTICATION_FAILED);
    }

}