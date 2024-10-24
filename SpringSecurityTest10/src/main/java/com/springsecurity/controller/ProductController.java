package com.springsecurity.controller;

import com.springsecurity.model.Product;
import com.springsecurity.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        System.out.println(product);

        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PatchMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {

        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {

        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @GetMapping("/viewProducts")
    public ResponseEntity<?> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/orderProduct/{productId}")
    public ResponseEntity<?> orderProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.orderProduct(productId));
    }

    @GetMapping("/contact")
    public ResponseEntity<?> contact() {

        return ResponseEntity.ok(productService.contact());
    }

    @GetMapping("/service")
    public ResponseEntity<?> service() {

        return ResponseEntity.ok(productService.service());
    }

}
