package com.fornesb.backend_ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fornesb.backend_ecommerce.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name= "users")
@Getter
@Setter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Roles role = Roles.USER;
    private String address;
    private String phoneNumber;
    private boolean isActive;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    public User(Integer id, String name, String email, String password, String role, String address, String phoneNumber, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Roles.valueOf(role);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;


    }

    public User() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPassword(String rawPassword) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(rawPassword);
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public void setEncryptedPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}
