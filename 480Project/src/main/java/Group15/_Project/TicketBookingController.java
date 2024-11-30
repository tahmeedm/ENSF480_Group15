package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket-bookings")
public class TicketBookingController {

    @Autowired
    private TicketBookingService ticketBookingService;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @GetMapping
    public ResponseEntity<List<TicketBooking>> getAllTicketBookings() {
        List<TicketBooking> ticketBookings = ticketBookingService.getAllTicketBookings();
        return new ResponseEntity<>(ticketBookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketBooking> getTicketBookingById(@PathVariable Long id) {
        Optional<TicketBooking> ticketBooking = ticketBookingService.getTicketBookingById(id);
        return ticketBooking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/screening/{screeningId}")
    public ResponseEntity<List<TicketBooking>> getTicketBookingsByScreening(@PathVariable Long screeningId) {
        Optional<Screening> screening = screeningRepository.findById(screeningId);
        return screening.map(value -> {
            List<TicketBooking> ticketBookings = ticketBookingService.getTicketBookingsByScreening(value);
            return new ResponseEntity<>(ticketBookings, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/receipt/{receiptId}")
    public ResponseEntity<TicketBooking> getTicketBookingByReceipt(@PathVariable Long receiptId) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptId);
        return receipt.map(value -> ticketBookingService.getTicketBookingByReceipt(value)
                .map(ticketBooking -> new ResponseEntity<>(ticketBooking, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TicketBooking> createTicketBooking(@RequestBody TicketBooking ticketBooking) {
        TicketBooking createdTicketBooking = ticketBookingService.createTicketBooking(ticketBooking);
        return new ResponseEntity<>(createdTicketBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketBooking> updateTicketBooking(@PathVariable Long id, @RequestBody TicketBooking ticketBookingDetails) {
        TicketBooking updatedTicketBooking = ticketBookingService.updateTicketBooking(id, ticketBookingDetails);
        return updatedTicketBooking != null ? new ResponseEntity<>(updatedTicketBooking, HttpStatus.OK)
                                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketBooking(@PathVariable Long id) {
        return ticketBookingService.deleteTicketBooking(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                                              : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
