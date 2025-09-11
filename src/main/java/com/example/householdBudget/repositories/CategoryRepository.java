package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = """ 
            SELECT c.*
            FROM Category c
            WHERE c.category_name = :categoryName
            """, nativeQuery = true)
    List<CategoryEntity> findCategoryByName(@Param("categoryName") String categoryName);

 /*   @Query(value = """
            SELECT c.*
            FROM Category c
            WHERE c.user_table_id = :userId;
            """, nativeQuery = true)
    List<CategoryEntity> findCategoryForUserId(@Param("userId") Long userId);*/
}
