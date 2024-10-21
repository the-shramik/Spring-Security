package com.springsecurity.service;

import com.springsecurity.model.Product;
import com.springsecurity.model.Purchase;
import com.springsecurity.repository.IProductRepository;
import com.springsecurity.repository.IPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl {

    private final IPurchaseRepository purchaseRepository;

    private final IProductRepository productRepository;

    public String purchaseProducts(@RequestBody List<Purchase> purchases){

        purchases.forEach(purchase -> {
             AtomicReference<Double> purchaseAmount= new AtomicReference<>(0.0);
             purchase.getProducts().forEach(product -> {
                 Product product1 =productRepository.findById(product.getProductId()).get();

                 purchaseAmount.set(product1.getProductPrice() * purchase.getPurchaseQuantity());

             });
             purchase.setProducts(purchase.getProducts());

             purchase.setPurchaseAmount(purchaseAmount.get());
             purchaseRepository.save(purchase);
        });

        return "Product purchase successful!";
    }

    public String cancelPurchase(@PathVariable Long purchaseId){

        purchaseRepository.deleteById(purchaseId);

        return "Purchase deleted!";
    }

    public List<Purchase> viewAllPurchases(){

        return purchaseRepository.findAll();
    }
}
