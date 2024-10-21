package com.springsecurity.controller;

import com.springsecurity.model.Order;
import com.springsecurity.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping("/orderProduct")
    public ResponseEntity<?> orderProduct(@RequestBody Order order){

        return ResponseEntity.ok(orderService.orderProduct(order));
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId){

        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }
}
