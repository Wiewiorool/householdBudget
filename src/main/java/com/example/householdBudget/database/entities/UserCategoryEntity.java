package com.example.householdBudget.database.entities;

import com.example.householdBudget.database.UserCategoryId;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "User_category")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCategoryEntity {
    @EmbeddedId
    private UserCategoryId userCategoryId;

    @ManyToOne
    @MapsId("userTableId")
    @JoinColumn(name = "user_table_id",nullable = false)
    private UserTableEntity user;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity category;

}
