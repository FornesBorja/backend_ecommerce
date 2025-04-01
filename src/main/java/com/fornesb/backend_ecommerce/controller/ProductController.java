package com.fornesb.backend_ecommerce.controller;

import com.fornesb.backend_ecommerce.entity.Product;
import com.fornesb.backend_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }
}
