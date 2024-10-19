package com.springsecurity.controller;

import com.springsecurity.model.Contact;
import com.springsecurity.repository.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
public class ContactController {

    @Autowired
    private IContactRepository contactRepository;

    @PostMapping("/contact")
    public ResponseEntity<?> saveContactInquiryDetails(@RequestBody Contact contact) {

        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    public String getServiceReqNumber() {
        Random random = new Random();

        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
