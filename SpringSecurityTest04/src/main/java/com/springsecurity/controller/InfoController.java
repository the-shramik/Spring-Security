package com.springsecurity.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/myInfo")
    public ResponseEntity<?> attendanceDetails(){
        return ResponseEntity.ok("Here is an your information !");
    }
}
