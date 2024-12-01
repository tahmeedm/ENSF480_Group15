package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a REST controller for handling ticket booking-related HTTP requests.
 * It maps to the "/ticket-bookings" URL path.
 */
@RestController
@RequestMapping("/ticket-bookings")
public class TicketBookingController {

    // Injects the TicketBookingService to interact with ticket booking data
    @Autowired
    private TicketBookingService ticketBookingService;

    // Injects the ScreeningRepository to access screening data
    @Autowired
    private ScreeningRepository screeningRepository;

    // Injects the ReceiptRepository to access receipt data
    @Autowired
    private ReceiptRepository receiptRepository;

    /**
     * Handles GET requests to retrieve all ticket bookings.
     * @return a list of all ticket bookings with HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<TicketBooking>> getAllTicketBookings() {
        List<TicketBooking> ticketBookings = ticketBookingService.getAllTicketBookings();
        return new ResponseEntity<>(ticketBookings, HttpStatus.OK);
    }

    /**
     * Handles GET requests to retrieve a ticket booking by its ID.
     * @param id the ID of the ticket booking to retrieve.
     * @return the ticket booking with the given ID, or HTTP status NOT_FOUND if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketBooking> getTicketBookingById(@PathVariable Long id) {
        Optional<TicketBooking> ticketBooking = ticketBookingService.getTicketBookingById(id);
        return ticketBooking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Handles GET requests to retrieve ticket bookings by screening ID.
     * @param screeningId the ID of the screening to retrieve ticket bookings for.
     * @return a list of ticket bookings for the given screening, or HTTP status NOT_FOUND if the screening is not found.
     */
    @GetMapping("/screening/{screeningId}")
    public ResponseEntity<List<TicketBooking>> getTicketBookingsByScreening(@PathVariable Long screeningId) {
        Optional<Screening> screening = screeningRepository.findById(screeningId);
        return screening.map(value -> {
            List<TicketBooking> ticketBookings = ticketBookingService.getTicketBookingsByScreening(value);
            return new ResponseEntity<>(ticketBookings, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Handles GET requests to retrieve a ticket booking by receipt ID.
     * @param receiptId the ID of the receipt to retrieve the ticket booking for.
     * @return the ticket booking with the given receipt, or HTTP status NOT_FOUND if not found.
     */
    @GetMapping("/receipt/{receiptId}")
    public ResponseEntity<TicketBooking> getTicketBookingByReceipt(@PathVariable Long receiptId) {
        Optional<Receipt> receipt = receiptRepository.findById(receiptId);
        return receipt.map(value -> ticketBookingService.getTicketBookingByReceipt(value)
                .map(ticketBooking -> new ResponseEntity<>(ticketBooking, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Handles POST requests to create a new ticket booking.
     * @param ticketBooking the ticket booking to create.
     * @return the created ticket booking with HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<TicketBooking> createTicketBooking(@RequestBody TicketBooking ticketBooking) {
        TicketBooking createdTicketBooking = ticketBookingService.createTicketBooking(ticketBooking);
        return new ResponseEntity<>(createdTicketBooking, HttpStatus.CREATED);
    }

    /**
     * Handles PUT requests to update an existing ticket booking.
     * @param id the ID of the ticket booking to update.
     * @param ticketBookingDetails the new details of the ticket booking.
     * @return the updated ticket booking with HTTP status OK, or HTTP status NOT_FOUND if the booking is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TicketBooking> updateTicketBooking(@PathVariable Long id, @RequestBody TicketBooking ticketBookingDetails) {
        TicketBooking updatedTicketBooking = ticketBookingService.updateTicketBooking(id, ticketBookingDetails);
        return updatedTicketBooking != null ? new ResponseEntity<>(updatedTicketBooking, HttpStatus.OK)
                                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Handles DELETE requests to remove a ticket booking by its ID.
     * @param id the ID of the ticket booking to delete.
     * @return HTTP status NO_CONTENT if the booking is deleted, or HTTP status NOT_FOUND if the booking is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketBooking(@PathVariable Long id) {
        return ticketBookingService.deleteTicketBooking(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

