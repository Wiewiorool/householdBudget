package com.example.householdBudget.service;

import com.example.householdBudget.database.entities.PriceEntity;
import com.example.householdBudget.database.entities.ProductEntity;
import com.example.householdBudget.database.entities.ReceiptEntity;
import com.example.householdBudget.repositories.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class PriceService {
    private final PriceRepository priceRepository;


    public PriceService(@Autowired PriceRepository priceRepository
    ) {
        this.priceRepository = priceRepository;

    }

    public PriceEntity addNewProductPrice(BigDecimal productPrice, ProductEntity productEntity, ReceiptEntity receiptId) {

        PriceEntity newProductPrice = PriceEntity.builder()
                .productPrice(productPrice)
                .product(productEntity)
                .receipt(receiptId)
                .build();
        return priceRepository.save(newProductPrice);
    }

    public PriceEntity findPriceForProductId(long productId) {
        List<PriceEntity> price = priceRepository.findByProductId(productId);

        if (price.isEmpty()) {
            log.info("Price not found for that product id: " + price);
            return null;
        }
        return price.getFirst();
    }
}
