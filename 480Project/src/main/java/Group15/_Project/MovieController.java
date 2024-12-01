package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST controller, allowing it to handle HTTP requests
@RestController
// Base URL mapping for the endpoints in this controller
@RequestMapping("/movies")
public class MovieController {

    // Injects the MovieService bean automatically
    @Autowired
    private MovieService movieService;

    // Handles GET requests to retrieve a movie by its name
    @GetMapping("/name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        // Uses the movieService to find a movie by its name
        Optional<Movie> movie = movieService.findByName(name);
        // If the movie is found, return it with a 200 OK status, else return 404 Not Found
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Handles GET requests to retrieve movies by their release date
    @GetMapping("/release-date/{releaseDate}")
    public ResponseEntity<List<Movie>> getMoviesByReleaseDate(@PathVariable String releaseDate) {
        // Uses the movieService to find movies by their release date
        List<Movie> movies = movieService.findByReleaseDate(releaseDate);
        // If movies are found, return them with a 200 OK status, else return 404 Not Found
        return movies.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(movies);
    }

    // Handles POST requests to create a new movie
    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        // Check if a movie with the same name already exists
        if (movieService.existsByName(movie.getName())) {
            // If it exists, return 400 Bad Request
            return ResponseEntity.badRequest().body(null);
        }
        // Save the new movie using the movieService
        Movie savedMovie = movieService.save(movie);
        // Return the saved movie with a 200 OK status
        return ResponseEntity.ok(savedMovie);
    }

    // Handles DELETE requests to remove a movie by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        // Uses the movieService to find a movie by its ID
        Optional<Movie> movie = movieService.findByName(id.toString());
        // If the movie is found, delete it and return 204 No Content
        if (movie.isPresent()) {
            movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            // If not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}

