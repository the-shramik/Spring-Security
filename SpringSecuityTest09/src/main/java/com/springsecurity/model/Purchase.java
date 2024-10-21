package com.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "purchase_info")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private Double purchaseAmount;

    private String purchaseDate;

    private Integer purchaseQuantity;

    @ManyToMany
    @JoinTable(
            name = "product_purchases",
            joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "purchaseId"),
            inverseJoinColumns = @JoinColumn(name = "prouct_id", referencedColumnName = "productId"))
    private List<Product> products;
}
