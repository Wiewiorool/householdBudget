package com.example.householdBudget.database.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Category")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "user_table_id", nullable = false)
    private UserTableEntity userTable;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", userTableId=" + userTable +
                '}';
    }
}
