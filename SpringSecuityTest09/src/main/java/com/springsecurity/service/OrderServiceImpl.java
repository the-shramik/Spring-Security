package com.springsecurity.service;

import com.springsecurity.model.Order;
import com.springsecurity.model.Product;
import com.springsecurity.repository.IOrderRepository;
import com.springsecurity.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    public final IOrderRepository orderRepository;

    public final IProductRepository productRepository;
    public Order orderProduct(Order order){

         Product product =productRepository.findById(order.getProduct().getProductId()).get();
         order.setOrderAmount(product.getProductPrice()*order.getOrderQuantity());

         return orderRepository.save(order);
    }

    public String cancelOrder(Long orderId){

        orderRepository.deleteById(orderId);

        return "Order cancelled successfully..!";
    }
}
