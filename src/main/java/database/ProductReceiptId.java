package database;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProductReceiptId implements Serializable {
    @Column(name = "product_id")
    private long productId;
    @Column(name = "receipt_id")
    private long receiptId;

}

