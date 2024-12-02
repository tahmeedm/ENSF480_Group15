package Group15._Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByTransactionDate(String transactionDate);
}

