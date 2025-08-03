package database.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Product")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private CategoryEntity category;

    @Column(name = "product_name")
    private String productName;

}

