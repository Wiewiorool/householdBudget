package database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "User_table")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_table_id")
    private long userTableId;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<UserCategoryEntity> userCategories;
}
