package com.fornesb.backend_ecommerce.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "order_items")
public class Order_item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    public Order_item(Integer price, Integer quantity, Product product, Order order, Integer id) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
        this.id = id;
    }

    public Order_item() {
    }
}
