package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        Optional<Movie> movie = movieService.findByName(name);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/release-date/{releaseDate}")
    public ResponseEntity<List<Movie>> getMoviesByReleaseDate(@PathVariable String releaseDate) {
        List<Movie> movies = movieService.findByReleaseDate(releaseDate);
        return movies.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(movies);
    }

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        if (movieService.existsByName(movie.getName())) {
            return ResponseEntity.badRequest().body(null);
        }
        Movie savedMovie = movieService.save(movie);
        return ResponseEntity.ok(savedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findByName(id.toString());
        if (movie.isPresent()) {
            movieService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
