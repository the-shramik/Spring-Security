package com.springsecurity.controller;

import com.springsecurity.model.Customer;
import com.springsecurity.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Registration successfully done..!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed..!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());

        return optionalCustomer.orElse(null);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(){

        customerRepository.findAll().forEach(customer -> {
            customer.getAuthorities().forEach(authority -> {
                System.out.println(authority.getName());
            });
            System.out.println(customer.getAuthorities());
        });
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/getCustomerWithAuthorities/{id}")
    public ResponseEntity<?> getCustomerWithAuth(@PathVariable Long id){
        return ResponseEntity.ok(customerRepository.findCustomerWithAuthorities(id));
    }
}
