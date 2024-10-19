package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/addUser")
    private ResponseEntity<?> addUser(@RequestBody User user){
         return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/getUsers")
    private ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
