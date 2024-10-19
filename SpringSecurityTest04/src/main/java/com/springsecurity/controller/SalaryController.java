package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    @GetMapping("/mySalary")
    public ResponseEntity<?> salaryDetails(){
        return ResponseEntity.ok("Here is an your salary details!");
    }
}
