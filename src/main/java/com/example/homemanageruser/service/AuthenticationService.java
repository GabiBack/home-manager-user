package com.example.homemanageruser.service;

import com.example.homemanageruser.defaults.AuthenticationDefaults;
import com.example.homemanageruser.model.authentication.Token;
import com.example.homemanageruser.model.authentication.VerificationTokenEntity;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.repository.AuthenticationRepository;
import com.example.homemanageruser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthenticationRepository authenticationRepository;

    public void saveVerificationTokenForUser(String token, UserEntity userEntity) {
        VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(token, userEntity);
        authenticationRepository.save(verificationTokenEntity);
    }

    public String validateVerificationToken(String token) {
        VerificationTokenEntity verificationToken = authenticationRepository.findByToken(token);
        if (verificationToken==null){
            return Token.INVALID.toString();
        }
        UserEntity userEntity = verificationToken.getUserEntity();
        Calendar calendar = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime()-calendar.getTime().getTime()) <=0){
            authenticationRepository.delete(verificationToken);
            return Token.EXPIRED.toString();
        }

        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        return Token.VALID.toString();
    }


    public String applicationUrl(HttpServletRequest request) {
        return AuthenticationDefaults.HTTP +
                request.getServerName() +
                AuthenticationDefaults.COLON +
                request.getServerPort() +
                request.getContextPath();
    }
}
