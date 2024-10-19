package com.springsecurity.controller;

import com.springsecurity.model.MyUser;
import com.springsecurity.service.MyUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyUserController {

    @Autowired
    private MyUserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody MyUser user){
       try{
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
       }
       catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }
}
