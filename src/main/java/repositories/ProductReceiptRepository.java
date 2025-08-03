package repositories;

import database.entities.ProductReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReceiptRepository extends JpaRepository<ProductReceiptEntity,Long> {
}
