package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findBySeatNumber(int seatNumber);

    @Query("SELECT s FROM Seat s JOIN s.screening ssl WHERE ssl.id = :screeningId")
    List<Seat> findByScreening(Screening screening);

    List<Seat> findByOccupant(User occupant);
}

