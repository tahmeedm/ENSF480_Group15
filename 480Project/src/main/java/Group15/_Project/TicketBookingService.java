package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketBookingService {

    @Autowired
    private final TicketBookingRepository ticketBookingRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatService seatService;

    public TicketBookingService(TicketBookingRepository ticketBookingRepository, ScreeningRepository screeningRepository, SeatService seatService) {
        this.ticketBookingRepository = ticketBookingRepository;
        this.screeningRepository = screeningRepository;
        this.seatService = seatService;
    }

    public List<TicketBooking> getAllTicketBookings() {
        return ticketBookingRepository.findAll();
    }

    public Optional<TicketBooking> getTicketBookingById(Long id) {
        return ticketBookingRepository.findById(id);
    }

    public List<TicketBooking> getTicketBookingsByScreening(Screening screening) {
        return ticketBookingRepository.findByScreening(screening);
    }

    public Optional<TicketBooking> getTicketBookingByReceipt(Receipt receipt) {
        return ticketBookingRepository.findByReceipt(receipt);
    }

    public TicketBooking createTicketBooking(TicketBooking ticketBooking, Long screeningId, List<Seat> seats) {
        Optional<Screening> optionalScreening = screeningRepository.findById(screeningId);
        if (optionalScreening.isPresent()) {
            Screening screening = optionalScreening.get();
            ticketBooking.setScreening(screening);

            for (Seat seat : seats) {
                seatService.createSeat(seat);
                seat.setTicketBooking(ticketBooking);
            }
            ticketBooking.setSeats(seats);

            try {
                TicketBooking savedTicketBooking = ticketBookingRepository.save(ticketBooking); // Save ticket booking with linked screening
                System.out.println("Returning ticket booking with ID: " + savedTicketBooking.getId() + " and Screening ID: " + screeningId);
                return savedTicketBooking;
            } catch (Exception e) {
                System.out.println("An error occurred while saving the ticket booking: " + e.getMessage());
                return null;
            }
        } else {
            throw new RuntimeException("Screening not found with ID: " + screeningId);
        }
    }

    public TicketBooking updateTicketBooking(Long id, TicketBooking ticketBookingDetails) {
        if (ticketBookingRepository.existsById(id)) {
            ticketBookingDetails.setId(id);
            return ticketBookingRepository.save(ticketBookingDetails);
        }
        return null;
    }

    public boolean deleteTicketBooking(Long id) {
        if (ticketBookingRepository.existsById(id)) {
            ticketBookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}