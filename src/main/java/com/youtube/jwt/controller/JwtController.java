package com.youtube.jwt.controller;

import com.youtube.jwt.entity.JwtRequest;
import com.youtube.jwt.entity.JwtResponse;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.JwtService;
import com.youtube.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {

            User user = this.userService.loadUserByUserName(jwtRequest.getUserName());
        System.out.println(user.getUserName() + " " + jwtRequest.getUserName());
            if(passwordEncoder.matches(jwtRequest.getUserPassword(), user.getUserPassword())) {
                System.out.println(user.getUserPassword() + " " + getEncodedPassword(jwtRequest.getUserPassword()));
              return jwtService.createJwtToken(jwtRequest);
            }
        System.out.println(user.getUserName() + " " + jwtRequest.getUserName());
        System.out.println(user.getUserPassword() + " " + getEncodedPassword(jwtRequest.getUserPassword()));
            throw new Exception("Invalid Credentials");
        }
}
