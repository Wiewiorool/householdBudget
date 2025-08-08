package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.UserTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTableRepository extends JpaRepository<UserTableEntity, Long> {
    List<UserTableEntity> findByNameAndSurname(@Param("name") String userFirstName,
                                               @Param("surname") String userLastName);

}
