package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public ResponseEntity<?> getContactInquiryDetails(){
        return ResponseEntity.ok("Inquiry details are saved into DB...!");
    }
}
