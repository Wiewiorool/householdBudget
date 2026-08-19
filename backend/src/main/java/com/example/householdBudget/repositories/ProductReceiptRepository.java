package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.ProductReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReceiptRepository extends JpaRepository<ProductReceiptEntity,Long> {
}
