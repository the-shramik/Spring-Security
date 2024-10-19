package com.springsecurity.service;

import com.springsecurity.model.Product;
import com.springsecurity.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {

    @Autowired
    private IProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public String deleteProduct(Long productId){
        productRepository.deleteById(productId);

        if (productRepository.findById(productId).isEmpty()){
            return "Product deleted successfully..!";
        }
        else {
            return "Product deletion failed..!";
        }
    }

    public Product updateProduct(Product product){
        Product existedProduct=productRepository.findById(product.getProductId()).get();

        existedProduct.setProductName(product.getProductName());
        existedProduct.setProductPrice(product.getProductPrice());
        existedProduct.setDescription(product.getDescription());

        return productRepository.save(existedProduct);
    }

    public String viewProduct(Long productId){
        Product product=productRepository.findById(productId).get();

        return "Product-name: "+product.getProductName()+"\n"+"Product-price: "+product.getProductPrice()+"\n"+"Description: "+product.getDescription();
    }

    public String orderProduct(Long productId){
        Product product=productRepository.findById(productId).get();
        return product.getProductName()+" purchased successfully...!";
    }
}
