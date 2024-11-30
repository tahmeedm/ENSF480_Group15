package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/transaction-date/{transactionDate}")
    public ResponseEntity<List<Receipt>> getReceiptsByTransactionDate(@PathVariable String transactionDate) {
        List<Receipt> receipts = receiptService.findByTransactionDate(transactionDate);
        return receipts.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(receipts);
    }

    @PostMapping("/create")
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {
        if (receiptService.existsByTransactionDate(receipt.getTransactionDate())) {
            return ResponseEntity.badRequest().body(null);
        }
        Receipt savedReceipt = receiptService.save(receipt);
        return ResponseEntity.ok(savedReceipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        if (receiptService.existsByTransactionDate(id.toString())) {
            receiptService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
