package Group15._Project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getSeatsByUser(RegisteredUser user) {
        return seatRepository.findByOccupant(user);
    }

    public Seat assignRegisteredUser(Long seatId, RegisteredUser user) {
        Seat seat = (Seat) seatRepository.findById(seatId).orElseThrow();
        seat.setRegisteredUser(user);
        seat.setOrdinaryUserId(null);
        return (Seat) seatRepository.save(seat);
    }

    public Seat assignOrdinaryUser(Long seatId, String ordId) {
        Seat seat = (Seat) seatRepository.findById(seatId).orElseThrow();
        seat.setRegisteredUser(null);
        seat.setOrdinaryUserId(ordId);
        return (Seat) seatRepository.save(seat);
    }

    public Seat updateSeat(Long id, Seat seatDetails) {
        if (seatRepository.existsById(id)) {
            seatDetails.setId(id);
            return (Seat) seatRepository.save(seatDetails);
        }
        return null;
    }

    public Seat createSeat(Seat seat) {
        return (Seat) seatRepository.save(seat);
    }
}
