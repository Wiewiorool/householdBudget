package com.example.householdBudget;

import com.example.householdBudget.database.entities.PriceEntity;
import com.example.householdBudget.database.entities.ProductEntity;
import com.example.householdBudget.database.entities.ReceiptEntity;
import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class HouseholdBudgetApplication {

    private static final Logger log = LoggerFactory.getLogger(HouseholdBudgetApplication.class);


    public static void main(String[] args) {
        String firstName = "Adam";
        String lastName = "Smith";
        String productName = "milk";
        String categoryName = "groceries";
        BigDecimal milkPrice = BigDecimal.valueOf(3.99);
        BigDecimal waterPrice = BigDecimal.valueOf(2.59);
        BigDecimal receiptPrice = BigDecimal.valueOf(2.59 + 3.99);


        ConfigurableApplicationContext context = SpringApplication.run(HouseholdBudgetApplication.class, args);

        UserService userService = context.getBean(UserService.class);
        UserTableEntity findUser = userService.findByNameAndSurname(firstName, lastName); //sprawdzanie czy user istnieje jak nie
        if (findUser == null) {                                                               // to go dodaje jezeli znajdzie to wyciaga z bazy danych
            log.info("User not found, creating new user! ");
            findUser = userService.addNewUser(firstName, lastName);
        }

        CategoryService categoryService = context.getBean(CategoryService.class);
        long newCategoryId = categoryService.addNewCategory(categoryName, firstName, lastName);

        ProductService productService = context.getBean(ProductService.class); // szukanie produktu zwracanie lub tworzenie nowego
        ProductEntity findProduct = productService.findProductByNameProduct(productName);
        if (findProduct == null) {
            log.info("Product not found. Adding new Product.");
            findProduct = productService.addNewProduct(productName, categoryName);

        }

        PriceService priceService = context.getBean(PriceService.class);
        PriceEntity findPriceForProduct = priceService.findPriceForProductId(findProduct.getProductId());

        ReceiptService receiptService = context.getBean(ReceiptService.class);
        ReceiptEntity newReceipt = receiptService.registerNewReceipt(receiptPrice, findUser, findProduct);


        if (findPriceForProduct == null) {
            findPriceForProduct = priceService.addNewProductPrice(milkPrice, findProduct, newReceipt);

        } else if (findPriceForProduct.getReceipt() == null) {
            findPriceForProduct = priceService.addNewProductPrice(milkPrice, findProduct, newReceipt);
        }

    }
}
