package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketBookingService {

    @Autowired
    private final TicketBookingRepository ticketBookingRepository;
    private final ScreeningRepository screeningRepository;

    public TicketBookingService(TicketBookingRepository ticketBookingRepository, ScreeningRepository screeningRepository) {
        this.ticketBookingRepository = ticketBookingRepository;
        this.screeningRepository = screeningRepository;
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

    public TicketBooking createTicketBooking(TicketBooking ticketBooking, Long screeningId) {
    Optional<Screening> optionalScreening = screeningRepository.findById(screeningId);
    if (optionalScreening.isPresent()) {
        Screening screening = optionalScreening.get();
        ticketBooking.setScreening(screening);
        try {
            TicketBooking savedTicketBooking = ticketBookingRepository.save(ticketBooking);
            return savedTicketBooking;
        } catch (Exception e) {
            System.out.println("Error saving TicketBooking: " + e.getMessage());
            throw new RuntimeException("Error saving TicketBooking");
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
