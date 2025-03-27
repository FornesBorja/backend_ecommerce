package com.fornesb.backend_ecommerce.service;

import com.fornesb.backend_ecommerce.dto.LoginRequest;
import com.fornesb.backend_ecommerce.dto.LoginResponse;
import com.fornesb.backend_ecommerce.entity.User;
import com.fornesb.backend_ecommerce.enums.Roles;
import com.fornesb.backend_ecommerce.repository.UserRepository;
import com.fornesb.backend_ecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id " + id));
    }


    public User createUser(User user) {
        Roles role;

        if (user.getRole() == null) {
            user.setRole(Roles.USER);
        }
        String roleInput = user.getRole().name().toUpperCase();

        try {
            role = Roles.valueOf(roleInput);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol not valid: " + user.getRole());
        }

        if (role == Roles.ADMIN) {
            role = Roles.USER;
        }

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User newUserData) {
        return userRepository.findById(id).map(user -> {
            if (newUserData.getName() != null) {
                user.setName(newUserData.getName());
            }
            if (newUserData.getEmail() != null) {
                user.setEmail(newUserData.getEmail());
            }
            if (newUserData.getPassword() != null && !newUserData.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(newUserData.getPassword()));
            }
            if (newUserData.getAddress() != null) {
                user.setAddress(newUserData.getAddress());
            }
            if (newUserData.getPhoneNumber() != null) {
                user.setPhoneNumber(newUserData.getPhoneNumber());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }



    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public LoginResponse authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String password = user.getPassword();
        if (user == null) {
            throw new RuntimeException("Username is incorrect");
        };

        if (passwordEncoder.matches(request.getPassword(), password)) {
            throw new RuntimeException("Password is incorrect");
        }
        String token = jwtUtil.generateToken(user);

        return new LoginResponse(token, "Login successful");

    }

}
