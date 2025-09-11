package com.example.householdBudget.service;

import com.example.householdBudget.database.entities.CategoryEntity;
import com.example.householdBudget.database.entities.ProductEntity;
import com.example.householdBudget.database.entities.ReceiptEntity;
import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.repositories.CategoryRepository;
import com.example.householdBudget.repositories.PriceRepository;
import com.example.householdBudget.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(@Autowired ProductRepository productRepository,
                          @Autowired CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public ProductEntity addNewProduct(String productName, String categoryName) {
        CategoryEntity category = categoryService.findCategoryName(categoryName);

        ProductEntity prduct = ProductEntity.builder()
                .productName(productName)
                .category(category)
                .build();
        return productRepository.save(prduct);


    }

    public ProductEntity findProductByNameProduct(String productName) {

        List<ProductEntity> product = productRepository.findByProductName(productName);

        if (product.isEmpty()) {
            log.info("Product not found");
            return null;
        }
        if (product.size() > 1) {
            log.info("Found more than one product with that name ");
        }
        return product.get(0);
    }
    public ProductEntity findById(long productId) {
        Optional<ProductEntity> possibleProduct = productRepository.findById(productId);

        if (possibleProduct.isEmpty()) {
            log.info("Receipt does not exist" + productId);
            return null;

        }
        return possibleProduct.get();


    }
}
