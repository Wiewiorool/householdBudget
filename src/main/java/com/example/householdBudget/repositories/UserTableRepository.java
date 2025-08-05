package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.UserTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTableRepository extends JpaRepository<UserTableEntity, Long> {
}
