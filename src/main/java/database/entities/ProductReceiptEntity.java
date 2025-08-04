package database.entities;

import database.ProductReceiptId;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Product_receipt")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReceiptEntity {
    @EmbeddedId
    private ProductReceiptId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @MapsId("receiptId")
    @JoinColumn(name = "receipt_id", nullable = false)
    private ReceiptEntity receipt;


}
