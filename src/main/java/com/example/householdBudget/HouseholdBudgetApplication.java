package com.example.householdBudget;

import com.example.householdBudget.database.entities.*;
import com.example.householdBudget.repositories.PriceRepository;
import com.example.householdBudget.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@SpringBootApplication
public class HouseholdBudgetApplication {

    private static final Logger log = LoggerFactory.getLogger(HouseholdBudgetApplication.class);


    public static void main(String[] args) {
        String firstName = "Jan";
        String lastName = "Kowalski";
        String newUserName = "Tomasz";
        String newUserLastName = "Duda";
        String productName = "juice";
        String categoryName = "furniture";
        String newProductName = "water";
        String newCategoryName = "soft drink";
        Instant newDate = Instant.now();
        BigDecimal newPrice = new BigDecimal(666);
        BigDecimal milkPrice = BigDecimal.valueOf(1.99);
        BigDecimal waterPrice = BigDecimal.valueOf(3.00);
        BigDecimal receiptPrice = BigDecimal.valueOf(100);

        ConfigurableApplicationContext context = SpringApplication.run(HouseholdBudgetApplication.class, args);

        UserService userService = context.getBean(UserService.class);

        Optional<UserTableEntity> optionalUser = userService.findByNameAndSurname(firstName, lastName); //sprawdzanie czy user istnieje jak nie

        CategoryService categoryService = context.getBean(CategoryService.class);
        long newCategoryId = categoryService.addNewCategory(categoryName, firstName, lastName);

        CategoryEntity switchCategories = categoryService.updateCategory(newCategoryId, newCategoryName);
        System.out.println("Afete update " + switchCategories);

        ProductService productService = context.getBean(ProductService.class); // szukanie produktu zwracanie lub tworzenie nowego
        Optional<ProductEntity> optionalProduct = productService.findProductByProductName(productName);
        if (optionalProduct.isEmpty()) {
            log.info("Product not found. Adding new Product.");
            optionalProduct = Optional.of(productService.addNewProduct(productName, categoryName));
        }

        ProductEntity updatedProduct = productService.updateProduct(optionalProduct.get().getProductId(), newProductName);
        System.out.println(updatedProduct);

        PriceService priceService = context.getBean(PriceService.class);
        PriceRepository priceRepository = context.getBean(PriceRepository.class);
        Optional<PriceEntity> foundPriceForProductOptional = priceService.findPriceForProductId(optionalProduct.get().getProductId());

        if (foundPriceForProductOptional.isEmpty()) {
            foundPriceForProductOptional = Optional.of(priceService.addNewProductPrice(milkPrice, optionalProduct.get()));
            System.out.println(foundPriceForProductOptional);
        }

        ReceiptService receiptService = context.getBean(ReceiptService.class);
        ReceiptEntity newReceipt = receiptService.registerNewReceipt(receiptPrice, optionalUser.get(), optionalProduct.get(), Instant.now());

        ReceiptEntity updatedReceipt = receiptService.updateReceiptByUser(newReceipt.getReceiptId(), newDate, newPrice);
        System.out.println("Updated receipt: " + updatedReceipt);
    }
}
