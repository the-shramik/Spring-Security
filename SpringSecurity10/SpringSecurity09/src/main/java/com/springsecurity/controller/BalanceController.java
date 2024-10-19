package com.springsecurity.controller;

import com.springsecurity.model.AccountTransactions;
import com.springsecurity.repository.IAccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private IAccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public ResponseEntity<?> getBalanceDetails(@RequestParam long id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);

        if (accountTransactions != null) {
            return ResponseEntity.ok(accountTransactions);
        } else {
            return null;
        }
    }
}
