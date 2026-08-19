package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {
    @Query(value = """
            SELECT r.* FROM Receipt r
            WHERE r.user_table_id = :userId
            """, nativeQuery = true)
    List<ReceiptEntity> findUserReceipt(@Param("userId") Long userId);
}
