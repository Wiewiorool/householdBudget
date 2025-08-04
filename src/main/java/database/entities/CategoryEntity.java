package database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "Category")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<UserCategoryEntity> userCategories;
}
