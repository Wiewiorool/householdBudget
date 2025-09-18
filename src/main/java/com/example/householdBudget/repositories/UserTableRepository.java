package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.UserTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTableRepository extends JpaRepository<UserTableEntity, Long> {
    @Query(value = """
            SELECT u.* FROM User_table u
            WHERE u.surname = :userLastName AND u.name = :userFirstName
            """, nativeQuery = true)
    List<UserTableEntity> findByNameAndSurname(@Param("userFirstName") String userFirstName,
                                               @Param("userLastName") String userLastName);

    @Query(value = """
            SELECT u.* FROM User_table u
            WHERE u.surname = :lastName
            """, nativeQuery = true)
    List<UserTableEntity> findBySurname(@Param("lastName") String lastName);

}
