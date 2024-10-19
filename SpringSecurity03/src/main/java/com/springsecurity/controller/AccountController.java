package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails(){
        return ResponseEntity.ok("Here are account details from DB...!");
    }
}
