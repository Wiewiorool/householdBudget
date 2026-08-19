package com.example.householdBudget.service;

import com.example.householdBudget.database.entities.PriceEntity;
import com.example.householdBudget.database.entities.ProductEntity;
import com.example.householdBudget.repositories.PriceRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PriceService {
    private final PriceRepository priceRepository;
    private final ProductService productService;


    public PriceService(@Autowired PriceRepository priceRepository,
                        @Autowired ProductService productService
    ) {
        this.priceRepository = priceRepository;
        this.productService = productService;
    }

    @Transactional
    public PriceEntity addNewProductPrice(BigDecimal productPrice, ProductEntity productEntity) {
        Optional<ProductEntity> productEntities = productService.findById(productEntity.getProductId());

        if (productEntities.isEmpty()) {
            log.info("Price not found, creating new price! ");
            productEntities = Optional.of(productService.addNewProduct(productEntity.getProductName(), productEntity.getCategory().getCategoryName()));
        }
        PriceEntity newPrice = PriceEntity.builder()
                .productPrice(productPrice)
                .product(productEntities.get())
                .build();
        return newPrice;
    }

    public Optional<PriceEntity> findPriceForProductId(long productId) {
        List<PriceEntity> price = priceRepository.findByProductId(productId);

        if (price.isEmpty()) {
            log.info("Price not found for that product id: " + price);
            return Optional.empty();
        }
        return Optional.of(price.getFirst());
    }
}