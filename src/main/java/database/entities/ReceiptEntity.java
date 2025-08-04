package database.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Table(name = "Receipt")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private long receiptId;

    @Column(name = "date")
    private Instant date;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "user_table_id")
    private long userTableId;
}
