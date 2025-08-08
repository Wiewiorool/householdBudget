package com.example.householdBudget.database;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReceiptId implements Serializable {
    @Column(name = "product_id")
    private long productId;
    @Column(name = "receipt_id")
    private long receiptId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReceiptId that = (ProductReceiptId) o;
        return productId == that.productId && receiptId == that.receiptId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, receiptId);
    }
}

