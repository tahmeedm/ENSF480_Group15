package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {
    List<TicketBooking> findByScreening(Screening screening);

    Optional<TicketBooking> findByReceipt(Receipt receipt);
}
