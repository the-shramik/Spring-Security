package com.springsecurity.controller;

import com.springsecurity.model.Accounts;
import com.springsecurity.repository.IAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private IAccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails(@RequestParam long id) {

        Accounts accounts = accountsRepository.findByCustomerId(id);

        if (accounts != null) {
            return ResponseEntity.ok(accounts);
        } else {
            return null;
        }
    }
}
