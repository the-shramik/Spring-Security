package com.springsecurity.controller;


import com.springsecurity.model.Purchase;
import com.springsecurity.service.PurchaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseServiceImpl purchaseService;

    @PostMapping("/purchaseProducts")
    public ResponseEntity<?> purchaseProducts(@RequestBody List<Purchase> purchases){

        System.out.println(purchases);
        return ResponseEntity.ok(purchaseService.purchaseProducts(purchases));
    }

    @DeleteMapping("/cancelPurchase/{purchaseId}")
    public ResponseEntity<?> cancelPurchase(@PathVariable Long purchaseId){

        return ResponseEntity.ok(purchaseService.cancelPurchase(purchaseId));
    }

    @GetMapping("/viewAllPurchases")
    public ResponseEntity<?> viewAllPurchases(){

        return ResponseEntity.ok(purchaseService.viewAllPurchases());
    }
}
