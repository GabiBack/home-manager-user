package com.example.homemanageruser.service;

import com.example.homemanageruser.config.JwtService;
import com.example.homemanageruser.model.authentication.AuthenticationRequest;
import com.example.homemanageruser.model.authentication.AuthenticationResponse;
import com.example.homemanageruser.model.user.UserEntity;
import com.example.homemanageruser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()));


        UserEntity userEntity = repository.findByEmail(request.getEmail())
                .orElseThrow();

        if(request.getEmail().equals(userEntity.getEmail())){
        String jwtToken = jwtService.generateToken(userEntity);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        }else
            return null;
    }
}
