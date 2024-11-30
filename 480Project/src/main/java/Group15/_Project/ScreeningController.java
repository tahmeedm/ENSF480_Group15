package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<Screening>> getScreeningsByTheatre(@PathVariable Long theatreId) {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (!theatreOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Theatre theatre = theatreOptional.get();
        List<Screening> screenings = screeningService.findByTheatre(theatre);
        return screenings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(screenings);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Screening>> getScreeningsByMovie(@PathVariable Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (!movieOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Movie movie = movieOptional.get();
        List<Screening> screenings = screeningService.findByMovie(movie);
        return screenings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(screenings);
    }

    @GetMapping("/date/{screenDate}")
    public ResponseEntity<List<Screening>> getScreeningsByScreenDate(@PathVariable String screenDate) {
        List<Screening> screenings = screeningService.findByScreenDate(screenDate);
        return screenings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(screenings);
    }

    @PostMapping("/create")
    public ResponseEntity<Screening> createScreening(@RequestBody Screening screening) {
        Screening savedScreening = screeningService.save(screening);
        return ResponseEntity.ok(savedScreening);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable Long id) {
        screeningService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
