package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> findByTransactionDate(String transactionDate) {
        return receiptRepository.findByTransactionDate(transactionDate);
    }

    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public void deleteById(Long id) {
        receiptRepository.deleteById(id);
    }

    public boolean existsByTransactionDate(String transactionDate) {
        return !receiptRepository.findByTransactionDate(transactionDate).isEmpty();
    }
}
