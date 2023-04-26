package com.example.homemanageruser.service;

//import com.example.homemanageruser.config.JwtService;
import com.example.homemanageruser.model.authentication.AuthenticationRequest;
import com.example.homemanageruser.model.authentication.AuthenticationResponse;
import com.example.homemanageruser.model.authentication.VerificationTokenEntity;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.repository.AuthenticationRepository;
import com.example.homemanageruser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;

//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return "invalid";
        }
        UserEntity userEntity = verificationToken.getUserEntity();
        Calendar calendar = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime()-calendar.getTime().getTime()) <=0){
            authenticationRepository.delete(verificationToken);
            return "expired";
        }

        userEntity.setEnabled(true);
        userRepository.save(userEntity);
        return "valid";

    }
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;

//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        request.getEmail(),
////                        request.getPassword()));
//
//
//        UserEntity userEntity = repository.findByEmail(request.getEmail())
//                .orElseThrow();
//
//        if(request.getEmail().equals(userEntity.getEmail())){
//        String jwtToken = jwtService.generateToken(userEntity);
////        String password = passwordEncoder.encode(request.getPassword());
////        if(request.getEmail().equals(userEntity.getEmail()) && password.equals(userEntity.getPassword()))
//
//
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//        }else
//            return null;
//    }
}
