package database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "Price")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private long priceId;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "receipt_price")
    private BigDecimal receiptPrice;

    @JoinColumn(name = "product_id")
    @ManyToOne(cascade = CascadeType.ALL) //
    private ProductEntity product;

    @JoinColumn(name = "receipt_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private ReceiptEntity receipt;

}
