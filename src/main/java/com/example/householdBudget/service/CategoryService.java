package com.example.householdBudget.service;

import com.example.householdBudget.database.entities.CategoryEntity;
import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryService(@Autowired CategoryRepository categoryRepository,
                           @Autowired UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public CategoryEntity findCategoryName(String categoryName) {

        List<CategoryEntity> allCategoriesByName = categoryRepository.findCategoryByName(categoryName);

        if (allCategoriesByName.isEmpty()) {
            log.info("Category not found!");
            return null;
        }
        return allCategoriesByName.getFirst();
    }

    public long addNewCategory(String categoryName, String userName, String userSurname) {
        Optional<UserTableEntity> user = userService.findByNameAndSurname(userName, userSurname);

        if (user.isEmpty()) {
            log.info("User not found, creating new user! ");
            user = Optional.of(userService.addNewUser(userName, userSurname));
        }

        CategoryEntity newCategory = CategoryEntity.builder()
                .categoryName(categoryName)
                .userTable(user.get())
                .build();
        CategoryEntity savedNewCategory = categoryRepository.save(newCategory);
        return savedNewCategory.getCategoryId();
    }

    public CategoryEntity updateCategory(Long categoryId, String categoryName) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);

        if (category.isEmpty()) {
            throw new RuntimeException("Product not found " + categoryId);
        }
        category.get().setCategoryId(categoryId);
        category.get().setCategoryName(categoryName);

        return category.get();
    }
}