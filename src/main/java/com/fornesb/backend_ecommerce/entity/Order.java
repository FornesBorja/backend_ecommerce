package com.fornesb.backend_ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime orderdate;
    private String status;
    private Double total;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order(Integer id, User user, LocalDateTime orderdate, String status, Double total) {
        this.id = id;
        this.user = user;
        this.orderdate = orderdate;
        this.status = status;
        this.total = total;
    }

    public Order() {
    }
}
