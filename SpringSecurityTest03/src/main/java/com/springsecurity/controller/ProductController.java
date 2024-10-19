package com.springsecurity.controller;

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

    @GetMapping("/buyProduct")
    public ResponseEntity<?> buyProduct(){
        return ResponseEntity.ok("Product purchased successfully..!");
    }

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok("Here are the all the products..!");
    }

    @GetMapping("/productInfo")
    public ResponseEntity<?> productInfo(){
        return ResponseEntity.ok("Here is an product information..!");
    }
}
