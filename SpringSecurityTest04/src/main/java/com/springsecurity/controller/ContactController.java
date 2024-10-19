package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public ResponseEntity<?> contactDetails(){
        return ResponseEntity.ok("Here are contact details for the inquiry purpose!");
    }
}
