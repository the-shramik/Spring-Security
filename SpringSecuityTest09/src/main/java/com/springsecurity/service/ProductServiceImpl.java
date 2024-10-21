package com.springsecurity.service;

import com.springsecurity.model.Product;
import com.springsecurity.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        existedProduct.setHsnNumber(product.getHsnNumber());

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
}
