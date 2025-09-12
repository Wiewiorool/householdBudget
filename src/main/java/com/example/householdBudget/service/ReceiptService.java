package com.example.householdBudget.service;

import com.example.householdBudget.database.ProductReceiptId;
import com.example.householdBudget.database.entities.ProductEntity;
import com.example.householdBudget.database.entities.ProductReceiptEntity;
import com.example.householdBudget.database.entities.ReceiptEntity;
import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.repositories.ProductReceiptRepository;
import com.example.householdBudget.repositories.ReceiptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ProductReceiptRepository productReceiptRepository;


    public ReceiptService(@Autowired ReceiptRepository receiptRepository,
                          @Autowired ProductReceiptRepository productReceiptRepository) {
        this.receiptRepository = receiptRepository;
        this.productReceiptRepository = productReceiptRepository;
    }


    public ReceiptEntity registerNewReceipt(BigDecimal receiptPrice, UserTableEntity userTableEntity, ProductEntity productEntity) {

        Instant date = Instant.parse("2025-08-08T17:59:00Z");
        ProductReceiptEntity productReceipt = ProductReceiptEntity.builder()
                .id(ProductReceiptId.builder()
                        .productId(productEntity.getProductId())
                        /* .receiptId(newReceipt.getReceiptId())*/
                        .build())
                .product(productEntity)
/*.receipt(newReceipt)*/
                .build();

        ReceiptEntity newReceipt = ReceiptEntity.builder()
                .productReceipts((Set.of(productReceipt)))
                .userTableId(userTableEntity)
                .receiptPrice(receiptPrice)
                .date(date)
                .build();
        receiptRepository.save(newReceipt);

        System.out.println("added new Receipt" + newReceipt);
        return newReceipt;
    }

    /* JESZCZE DO DOPRACOWANIA

        public ReceiptEntity getReceiptByLastName(String lastName) {
            List<UserTableEntity> userTableEntities = userTableRepository.findBySurname(lastName);

            if (userTableEntities.isEmpty()) {
                log.info("User does not exist");
                throw new IllegalStateException("User not found!");
            } else if (userTableEntities.size() > 1) {
                log.info("Its more than one user with surname: " + lastName);
                throw new IllegalStateException("More than one user!");
            } else {
                log.info("User not found!");
            }
            UserTableEntity user = userTableEntities.getFirst();

            List<ReceiptEntity> userReceipt = receiptRepository.findUserReceipt(user.getUserTableId());

            if (userReceipt.isEmpty()) {
                log.info("No receipt found");
                throw new IllegalStateException("No receipt found");
            }
            if (userReceipt.size() > 1) {
                log.info("More than 1 receipt found");
                throw new IllegalStateException("More than 1 receipt found");
            }
            return userReceipt.getFirst();
        }
    */
    public ReceiptEntity findById(long receiptId) {
        Optional<ReceiptEntity> possibleReceipt = receiptRepository.findById(receiptId);

        if (possibleReceipt.isEmpty()) {
            log.info("Receipt does not exist" + receiptId);
            return null;

        }
        return possibleReceipt.get();


    }
}
