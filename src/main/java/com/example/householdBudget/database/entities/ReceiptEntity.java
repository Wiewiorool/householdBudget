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
@ToString
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private long receiptId;

    @Column(name = "date")
    private Instant date;

    @Column(name = "receipt_price")
    private BigDecimal receiptPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_table_id", referencedColumnName = "user_table_id")
    private UserTableEntity userTableId;

    @OneToMany(mappedBy = "receipt")
    private Set<ProductReceiptEntity> productReceipts;
}
