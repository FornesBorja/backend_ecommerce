package com.fornesb.backend_ecommerce.repository;

import com.fornesb.backend_ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Integer>{
    Optional<Product> findByName(String name);
}
