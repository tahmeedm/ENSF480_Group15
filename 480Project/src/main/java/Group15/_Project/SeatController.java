package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Seat>> getSeatsByUser(@PathVariable Long userId) {
        Optional<RegisteredUser> userOptional = registeredUserRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Seat> seats = seatService.getSeatsByUser(userOptional.get());
            return new ResponseEntity<>(seats, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seatDetails) {
        Seat updatedSeat = seatService.updateSeat(id, seatDetails);
        if (updatedSeat != null) {
            return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        Seat createdSeat = seatService.createSeat(seat);
        return new ResponseEntity<>(createdSeat, HttpStatus.CREATED);
    }
}
