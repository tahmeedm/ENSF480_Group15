package Group15._Project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is a REST controller for handling receipt-related HTTP requests
@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    // Autowiring the ReceiptService to interact with the data layer
    @Autowired
    private ReceiptService receiptService;

    // HTTP GET method to retrieve receipts by transaction date
    @GetMapping("/transaction-date/{transactionDate}")
    public ResponseEntity<List<Receipt>> getReceiptsByTransactionDate(@PathVariable String transactionDate) {
        // Fetch the list of receipts for the given transaction date
        List<Receipt> receipts = receiptService.findByTransactionDate(transactionDate);
        // If no receipts found, return 404 NOT FOUND, otherwise return 200 OK with the list of receipts
        return receipts.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(receipts);
    }

    // HTTP POST method to create a new receipt
    @PostMapping("/create")
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) {
        // Check if a receipt with the same transaction date already exists
        if (receiptService.existsByTransactionDate(receipt.getTransactionDate())) {
            // If it exists, return 400 BAD REQUEST
            return ResponseEntity.badRequest().body(null);
        }
        // Save the new receipt
        Receipt savedReceipt = receiptService.save(receipt);
        // Return 200 OK with the saved receipt
        return ResponseEntity.ok(savedReceipt);
    }

    // HTTP DELETE method to delete a receipt by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        // Check if a receipt with the given ID exists
        if (receiptService.existsByTransactionDate(id.toString())) {
            // If it exists, delete the receipt
            receiptService.deleteById(id);
            // Return 204 NO CONTENT to indicate successful deletion
            return ResponseEntity.noContent().build();
        } else {
            // If not found, return 404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }
}

