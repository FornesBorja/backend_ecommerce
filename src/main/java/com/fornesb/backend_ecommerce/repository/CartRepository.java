package com.fornesb.backend_ecommerce.repository;

import com.fornesb.backend_ecommerce.entity.Cart;
import com.fornesb.backend_ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository <Cart, Integer>
{
    Optional<Cart> findByUser(User user);
}
