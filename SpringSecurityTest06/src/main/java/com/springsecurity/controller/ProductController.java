package com.springsecurity.controller;

import com.springsecurity.model.Product;
import com.springsecurity.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PatchMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @GetMapping("/viewProduct/{productId}")
    public ResponseEntity<?> viewProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.viewProduct(productId));
    }

    @GetMapping("/buyProduct/{productId}")
    public ResponseEntity<?> buyProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.orderProduct(productId));
    }

}
