package com.example.springsecuritytest05.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/addProduct")
    public ResponseEntity<?> addProduct(){
        return ResponseEntity.ok("Product added successfully..!");
    }

    @GetMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(){
        return ResponseEntity.ok("Product updated successfully..!");
    }

    @GetMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(){
        return ResponseEntity.ok("Product deleted successfully..!");
    }

    @GetMapping("/viewProduct")
    public ResponseEntity<?> viewProduct(){
        return ResponseEntity.ok("Here is an product info..!");
    }

    @GetMapping("/orderProduct")
    public ResponseEntity<?> orderProduct(){
        return ResponseEntity.ok("Product ordered successfully..!");
    }
}
