package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/myLoans")
    public ResponseEntity<?> getLoansDetails(){
        return ResponseEntity.ok("Here are loans details from DB...!");
    }
}
