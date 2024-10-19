package com.springsecurity.controller;

import com.springsecurity.model.Authority;
import com.springsecurity.repository.IAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController {

    @Autowired
    private IAuthorityRepository authorityRepository;

    @PostMapping("/addAuthority")
    public ResponseEntity<?> addAuthority(@RequestBody Authority authority){
         return ResponseEntity.ok(authorityRepository.save(authority));
    }
}
