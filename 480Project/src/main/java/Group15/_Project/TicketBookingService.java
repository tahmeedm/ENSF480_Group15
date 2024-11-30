package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketBookingService {

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

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

    public TicketBooking createTicketBooking(TicketBooking ticketBooking) {
        return ticketBookingRepository.save(ticketBooking);
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
