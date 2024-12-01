/**
 * This class is a REST controller for handling seat-related HTTP requests.
 * 
 * It supports the following endpoints:
 *   GET /seats/user/{userId} - Retrieves a list of seats associated with a user
 *   PUT /seats/{id} - Updates a seat
 *   POST /seats - Creates a new seat
 */
package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This annotation marks this class as a REST controller, allowing it to handle HTTP requests.
 */
@RestController
/**
 * This annotation specifies the base path for all endpoints in this controller.
 */
@RequestMapping("/seats")
public class SeatController {

	/**
	 * This annotation injects an instance of the SeatService class, which encapsulates the business logic for
	 * manipulating seats in the database.
	 */
    @Autowired
    private SeatService seatService;

	/**
	 * This annotation injects an instance of the RegisteredUserRepository class, which encapsulates the
	 * business logic for manipulating registered users in the database.
	 */
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

	@Autowired
	private ScreeningRepository screeningRepository;

	/**
	 * This annotation specifies that this method handles GET requests to the /seats/user/{userId} endpoint.
	 * The userId parameter is a path variable, and is annotated as such with the @PathVariable annotation.
	 *
	 * This method retrieves a list of seats associated with the user with the specified userId, and returns them as
	 * a ResponseEntity with a list of Seat objects. If the user is not found, it returns a 404 status code.
	 */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Seat>> getSeatsByUser(@PathVariable Long userId) {
        Optional<RegisteredUser> userOptional = registeredUserRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Seat> seats = seatService.getSeatsByUser(userOptional.get());
            return new ResponseEntity<>(seats, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

	@GetMapping("/screening/{screeningId}")
	public ResponseEntity<List<Seat>> getSeatsByScreening(@PathVariable Long screeningId) {
		Optional<Screening> screening = screeningRepository.findById(screeningId);
		if (screening.isPresent()) {
			List<Seat> seats = seatService.getSeatsByScreening(screening.get());
			return new ResponseEntity<>(seats, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * This annotation specifies that this method handles PUT requests to the /seats/{id} endpoint.
	 * The id parameter is a path variable, and is annotated as such with the @PathVariable annotation.
	 *
	 * This method updates a seat with the specified id with the details in the request body, and returns the
	 * updated seat as a ResponseEntity with a single Seat object. If the seat is not found, it returns a 404
	 * status code.
	 */
    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seatDetails) {
        Seat updatedSeat = seatService.updateSeat(id, seatDetails);
        if (updatedSeat != null) {
            return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

	/**
	 * This annotation specifies that this method handles POST requests to the /seats endpoint.
	 *
	 * This method creates a new seat with the details in the request body, and returns the created seat as a
	 * ResponseEntity with a single Seat object. The created seat is returned with a 201 status code.
	 */
    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        Seat createdSeat = seatService.createSeat(seat);
        return new ResponseEntity<>(createdSeat, HttpStatus.CREATED);
    }
}

