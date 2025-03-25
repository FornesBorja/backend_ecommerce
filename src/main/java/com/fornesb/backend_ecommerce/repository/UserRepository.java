package com.fornesb.backend_ecommerce.repository;

import com.fornesb.backend_ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>
{
    <User> com.fornesb.backend_ecommerce.entity.User findByEmail(String email);
}
