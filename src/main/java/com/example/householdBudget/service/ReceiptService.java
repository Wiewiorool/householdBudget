package com.example.householdBudget.service;

import com.example.householdBudget.database.ProductReceiptId;
import com.example.householdBudget.database.entities.ProductEntity;
import com.example.householdBudget.database.entities.ProductReceiptEntity;
import com.example.householdBudget.database.entities.ReceiptEntity;
import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.repositories.ProductReceiptRepository;
import com.example.householdBudget.repositories.ReceiptRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
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

    @Transactional
    public ReceiptEntity registerNewReceipt(BigDecimal receiptPrice,
                                            UserTableEntity userTableEntity,
                                            ProductEntity productEntity,
                                            Instant date) {

        ReceiptEntity newReceipt = ReceiptEntity.builder()
                .userTableId(userTableEntity)
                .receiptPrice(receiptPrice)
                .date(date)
                .build();

        ReceiptEntity saved = receiptRepository.save(newReceipt);

        ProductReceiptEntity productReceipt = ProductReceiptEntity.builder()
                .id(ProductReceiptId.builder()
                        .productId(productEntity.getProductId())
                        .receiptId(saved.getReceiptId())
                        .build())
                .product(productEntity)
                .receipt(saved)
                .build();

        ProductReceiptEntity savedReceiptProductEntity = productReceiptRepository.save(productReceipt);

        saved.setProductReceipts(Set.of(savedReceiptProductEntity));

        System.out.println("added new Receipt" + saved);


        return saved;
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
