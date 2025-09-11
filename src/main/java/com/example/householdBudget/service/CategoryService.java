package com.example.householdBudget.service;

import com.example.householdBudget.database.entities.CategoryEntity;
import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryService(CategoryRepository categoryRepository,
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
        return allCategoriesByName.get(0);
    }

    public long addNewCategory(String categoryName,String userName, String userSurname) {
        UserTableEntity user = userService.findByNameAndSurname(userName, userSurname);
        CategoryEntity newCategory = CategoryEntity.builder()
                .categoryName(categoryName)
                .userTable(user)
                .build();
        CategoryEntity savedNewCategory = categoryRepository.save(newCategory);
        return savedNewCategory.getCategoryId();
    }

}
