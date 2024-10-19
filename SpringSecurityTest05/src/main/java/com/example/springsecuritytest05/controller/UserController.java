package com.example.springsecuritytest05.controller;

import com.example.springsecuritytest05.exception.UserAlreadyPresentException;
import com.example.springsecuritytest05.model.MyUser;
import com.example.springsecuritytest05.repository.IMyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IMyUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody MyUser user) throws UserAlreadyPresentException {
        if(userRepository.findByEmail(user.getEmail()).isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return ResponseEntity.ok(userRepository.save(user));
        }
        else {
            throw new UserAlreadyPresentException("Email is in use");
        }
    }
}
