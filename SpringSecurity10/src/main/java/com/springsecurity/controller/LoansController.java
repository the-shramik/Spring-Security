package com.springsecurity.controller;

import com.springsecurity.model.Loans;
import com.springsecurity.repository.ILoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private ILoansRepository loansRepository;

    @GetMapping("/myLoans")
    public ResponseEntity<?> getLoansDetails(@RequestParam long id) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(id);

        if (loans != null) {
            return ResponseEntity.ok(loans);
        } else {
            return null;
        }
    }
}
