package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity,Long> {
}
