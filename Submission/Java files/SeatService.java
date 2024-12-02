package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getSeatsByUser(RegisteredUser user) {
        return seatRepository.findByOccupant(user);
    }

    // getSeatsByScreening
    public List<Seat> getSeatsByScreening(Screening screening) {
        return seatRepository.findByScreening(screening);
    }

    public Seat updateSeat(Long id, Seat seatDetails) {
        if (seatRepository.existsById(id)) {
            seatDetails.setId(id);
            return seatRepository.save(seatDetails);
        }
        return null;
    }

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }
}
