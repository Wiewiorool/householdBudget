package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.UserCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategoryEntity,Long> {
}
