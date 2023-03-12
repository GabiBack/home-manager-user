package com.example.homemanageruser.controller;

import com.example.homemanageruser.config.JwtUtils;
import com.example.homemanageruser.model.AuthenticationRequest;
import com.example.homemanageruser.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController implements HomeManagerUserController{

    private AuthenticationManager authenticationManager;
    private AuthenticateService authenticateService;
    private JwtUtils jwtUtils;

    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));

        final UserDetails userDetails = authenticateService.findUserDetailsByEmail(request.getEmail());
        if(userDetails != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        } else {
            return ResponseEntity.status(400).body("Cannot authenticate");
        }
    }
}
