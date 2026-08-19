package com.example.householdBudget.repositories;

import com.example.householdBudget.database.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = """
            SELECT pr.* FROM Product pr
            WHERE pr.product_name = :productName
            """, nativeQuery = true)
    List<ProductEntity> findByProductName(@Param("productName") String productName);

    @Query(value = """
            SELECT * FROM Product 
            WHERE UPPER(product_name) LIKE UPPER(:productName)
            AND category_id = :categoryId
            """, nativeQuery = true)
    ProductEntity findSpecificProduct(@Param("productName") String productName,
                                      @Param("categoryId") Long categoryId);

/*
    List<ProductEntity> findCategoryForProductName(@Param())
*/
}
