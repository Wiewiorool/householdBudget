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
public class UserCategoryId implements Serializable {
    @Column(name="user_table_id")
    private long userTableId;
    @Column(name="category_id")
    private long categoryId;

}
