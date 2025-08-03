package database.entities;

import database.UserCategoryId;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "User_category")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCategoryEntity {
    @EmbeddedId
    private UserCategoryId userCategoryId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private ProductEntity productId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="receipt_id")
    private ReceiptEntity receiptId;
}
