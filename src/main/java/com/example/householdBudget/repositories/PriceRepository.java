package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query(value = """
            SELECT p.*
            FROM Price p
            WHERE p.product_id = :productId
            """, nativeQuery = true)
    List<PriceEntity> findByProductId(@Param("productId") Long productId);


}
