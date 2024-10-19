package com.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/addProduct")
    private ResponseEntity<?> addProduct(){
        return ResponseEntity.ok("Product added successfully..!");
    }

    @GetMapping("/updateProduct")
    private ResponseEntity<?> updateProduct(){
        return ResponseEntity.ok("Product updated successfully..!");
    }

    @GetMapping("/deleteProduct")
    private ResponseEntity<?> deleteProduct(){
        return ResponseEntity.ok("Product deleted successfully..!");
    }

    @GetMapping("/getProducts")
    private ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok("Here are the all products..!");
    }

    @GetMapping("/buyProduct")
    private ResponseEntity<?> buyProduct(){
        return ResponseEntity.ok("Product purchased successfully..!");
    }

    @GetMapping("/productInfo")
    private ResponseEntity<?> getProductInfo(){
        return ResponseEntity.ok("Here is an product info..!");
    }
}
