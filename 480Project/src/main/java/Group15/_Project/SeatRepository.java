package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findBySeatNumber(int seatNumber);

    List<Seat> findByScreening(Screening screening);

    List<Seat> findByOccupant(User occupant);
}

