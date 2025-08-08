package com.example.householdBudget.database.entities;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "User_table")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_table_id")
    private long userTableId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

}
