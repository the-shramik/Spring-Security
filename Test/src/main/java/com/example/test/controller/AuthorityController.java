package com.example.test.controller;

import com.example.test.model.Authority;
import com.example.test.model.User;
import com.example.test.repo.IAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController {

    @Autowired
    private IAuthorityRepository authorityRepository;

    @PostMapping("/addAuthority")
    private ResponseEntity<?> addUser(@RequestBody Authority authority){
        return ResponseEntity.ok(authorityRepository.save(authority));
    }

    @GetMapping("/getAuthorities")
    private ResponseEntity<?> addUser(){
        return ResponseEntity.ok(authorityRepository.findAll());
    }

}
