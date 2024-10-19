package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    @GetMapping("/myAttendance")
    public ResponseEntity<?> attendanceDetails(){
        return ResponseEntity.ok("Here is the monthly attendance !");
    }
}
