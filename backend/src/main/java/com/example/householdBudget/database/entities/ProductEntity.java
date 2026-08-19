package com.example.householdBudget.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "Product")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    @JoinColumn(name = "category_id",nullable = false)
    @ManyToOne
    private CategoryEntity category;

    @Column(name = "product_name")
    private String productName;

    @OneToMany(mappedBy = "product")
    private Set<ProductReceiptEntity> productReceipts;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productName='" + productName + '\'' +
                ", category=" + category +
                ", productId=" + productId +
                '}';
    }
}

