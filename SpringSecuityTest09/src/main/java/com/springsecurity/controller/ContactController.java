package com.springsecurity.controller;

import com.springsecurity.service.ContactServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactService;

    @GetMapping("/contact")
    public ResponseEntity<?> contact(){
        return ResponseEntity.ok(contactService.contact());
    }
}
