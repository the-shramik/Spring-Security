package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnouncementController {

    @GetMapping("/announcement")
    public ResponseEntity<?> announcementDetails(){
       return ResponseEntity.ok("Here the latest announcements for all the employees..!");
    }
}
