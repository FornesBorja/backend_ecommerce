package com.fornesb.backend_ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "cart_items")
public class Cart_item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    public Cart_item(Integer quantity, Product product, Cart cart, Integer id) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
        this.id = id;
    }

    public Cart_item() {
    }
}
