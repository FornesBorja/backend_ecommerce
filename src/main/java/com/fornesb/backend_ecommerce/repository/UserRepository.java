package com.fornesb.backend_ecommerce.repository;

import com.fornesb.backend_ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer>
{
    Optional<User> findByEmail(String email);
}
