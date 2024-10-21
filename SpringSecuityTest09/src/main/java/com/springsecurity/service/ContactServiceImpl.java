package com.springsecurity.service;

import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl {

    public String contact(){
        return "Here are the contact info!";
    }
}
