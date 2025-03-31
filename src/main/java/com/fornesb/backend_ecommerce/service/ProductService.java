package com.fornesb.backend_ecommerce.service;

import com.fornesb.backend_ecommerce.entity.Product;
import com.fornesb.backend_ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

}
