package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @GetMapping("/myAccount")
    public ResponseEntity<?> myAccount(){
        return ResponseEntity.ok("Here is your account details");
    }

    @GetMapping("/myLoans")
    public ResponseEntity<?> myLoans(){
        return ResponseEntity.ok("Here is your loans details");
    }

    @GetMapping("/myCards")
    public ResponseEntity<?> myCards(){
        return ResponseEntity.ok("Here is your cards details");
    }

    @GetMapping("/myBalance")
    public ResponseEntity<?> myBalance(){
        return ResponseEntity.ok("Here is your balance details");
    }

    @GetMapping("/contact")
    public ResponseEntity<?> contact(){
        return ResponseEntity.ok("Here are contact details for inquiry...!");
    }

    @GetMapping("/notices")
    public ResponseEntity<?> notices(){
        return ResponseEntity.ok("Here are recent notices....!");
    }
}
