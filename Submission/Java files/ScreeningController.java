package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Annotates this class as a REST controller, allowing it to handle HTTP requests
@RestController
// Maps HTTP requests to /screenings to this controller
@RequestMapping("/screenings")
public class ScreeningController {

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply CORS to all endpoints
            .allowedOrigins("http://localhost:3000")  // Allow requests from your frontend (adjust for your frontend's URL)
            .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow the relevant HTTP methods
            .allowedHeaders("*")  // Allow any headers
            .allowCredentials(true);  // Allow cookies if needed
    }
}

    // Injects an instance of ScreeningService into this controller
    @Autowired
    private ScreeningService screeningService;

    // Injects an instance of TheatreRepository into this controller
    @Autowired
    private TheatreRepository theatreRepository;

    // Injects an instance of MovieRepository into this controller
    @Autowired
    private MovieRepository movieRepository;

    // Handles GET requests for screenings by theatre ID
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<Screening>> getScreeningsByTheatre(@PathVariable Long theatreId) {
        System.out.println("Fetching theatre by ID: " + theatreId);
    
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (!theatreOptional.isPresent()) {
            System.out.println("Theatre not found for ID: " + theatreId);
            return ResponseEntity.notFound().build();
        }
    
        Theatre theatre = theatreOptional.get();
        System.out.println("Fetched theatre: " + theatre);
    
        List<Screening> screenings = screeningService.findByTheatre(theatre);
        System.out.println("Screenings for theatre: " + screenings);
    
        return screenings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(screenings);
    }

    // Handles GET requests for screenings by movie ID
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Screening>> getScreeningsByMovie(@PathVariable Long movieId) {
        // Retrieves the movie by ID
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        // If the movie is not found, return a 404 response
        if (!movieOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // If found, retrieve the list of screenings for that movie
        Movie movie = movieOptional.get();
        List<Screening> screenings = screeningService.findByMovie(movie);
        // Return 404 if no screenings are found, otherwise return the list of screenings
        return screenings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(screenings);
    }

    // Handles GET requests for screenings by screening date
    @GetMapping("/date/{screenDate}")
    public ResponseEntity<List<Screening>> getScreeningsByScreenDate(@PathVariable String screenDate) {
        // Retrieves the list of screenings by date
        List<Screening> screenings = screeningService.findByScreenDate(screenDate);
        // Return 404 if no screenings are found, otherwise return the list of screenings
        return screenings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(screenings);
    }

    // Handles GET request for movie id and screen date
    @GetMapping("/movie/{movieId}/date/{screenDate}")
    public ResponseEntity<List<Screening>> getScreeningsByMovieAndScreenDate(@PathVariable Long movieId, @PathVariable String screenDate) {
        // Retrieves the movie by ID
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        // If the movie is not found, return a 404 response
        if (!movieOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // If found, retrieve the list of screenings for that movie and date
        Movie movie = movieOptional.get();
        List<Screening> screenings = screeningService.findByMovieAndScreenDate(movie, screenDate);
        // Return 404 if no screenings are found, otherwise return the list of screenings
        return screenings.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(screenings);
    }

    // Handles POST requests to create a new screening
    @PostMapping("/create")
    public ResponseEntity<Screening> createScreening(@RequestBody Screening screening) {
        // Saves the new screening to the database
        Screening savedScreening = screeningService.save(screening);
        // Returns the saved screening
        return ResponseEntity.ok(savedScreening);
    }

    // Handles DELETE requests to delete a screening by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable Long id) {
        // Deletes the screening from the database by ID
        screeningService.deleteById(id);
        // Returns a 204 No Content response
        return ResponseEntity.noContent().build();
    }
}
