package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {



    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication.getName());
        return ResponseEntity.ok("Here are account details from DB...!");
    }
}
