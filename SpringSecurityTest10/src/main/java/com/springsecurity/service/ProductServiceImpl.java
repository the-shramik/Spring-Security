package com.springsecurity.service;

import com.springsecurity.model.Product;
import com.springsecurity.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    private final IProductRepository productRepository;

    public Product addProduct(Product product){

        return productRepository.save(product);
    }


    public Product updateProduct(Product product){

        Product existedProduct=productRepository.findById(product.getProductId()).get();

        existedProduct.setProductName(product.getProductName());
        existedProduct.setProductPrice(product.getProductPrice());

        return productRepository.save(existedProduct);
    }


    public String deleteProduct(Long productId){

        productRepository.deleteById(productId);

        if(productRepository.findById(productId).isEmpty()){
            return "product deleted successfully..!";
        }
        else{
            return "product not deleted !";
        }
    }


    public List<Product> getAllProducts(){

        return productRepository.findAll();
    }

    public String orderProduct(@PathVariable Long productId){

       Product product=productRepository.findById(productId).get();
       return product.getProductName()+" ordered successfully..!";
    }

    public String contact(){
        return "Here is an contact info for inquiry purpose!";
    }

    public String service(){
        return "Here are the our services!";
    }
}
