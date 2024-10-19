package com.springsecurity.controller;

import com.springsecurity.model.Employee;
import com.springsecurity.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerEmp(@RequestBody Employee employee) {
        try {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.registerEmployee(employee));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
