package com.fornesb.backend_ecommerce.service;

import com.fornesb.backend_ecommerce.entity.User;
import com.fornesb.backend_ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User newUserData) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUserData.getName());
                    user.setEmail(newUserData.getEmail());
                    user.setAddress(newUserData.getAddress());
                    user.setPhoneNumber(newUserData.getPhoneNumber());
                    return userRepository.save(user);
                }).orElse(null);
    }

    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
