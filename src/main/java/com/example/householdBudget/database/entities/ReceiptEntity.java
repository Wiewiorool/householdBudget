package com.example.householdBudget.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Table(name = "Receipt")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private long receiptId;

    @Column(name = "date")
    private Instant date;

    @Column(name = "receipt_price")
    private BigDecimal receiptPrice;

    @OneToOne
    @JoinColumn(name = "user_table_id", referencedColumnName = "user_table_id")
    private UserTableEntity userTableId;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ProductReceiptEntity> productReceipts;

    @Override
    public String toString() {
        return "ReceiptEntity{" +
                "receiptId=" + receiptId +
                ", " + date +
                ", receiptPrice=" + receiptPrice +
                ", userTableId=" + userTableId +
                ", productReceipts=" + productReceipts +
                '}';
    }
}
