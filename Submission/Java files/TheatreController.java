package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// This annotation marks this class as a REST controller, allowing it to handle HTTP requests.
@RestController
// Base URL path for all endpoints in this controller.
@RequestMapping("/theatres")
public class TheatreController {

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

    // Automatically inject an instance of TheatreService.
    @Autowired
    private TheatreService theatreService;

    // Handles GET requests to retrieve all theatres.
    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        // Fetches all theatres from the service.
        List<Theatre> theatres = theatreService.getAllTheatres();
        System.out.println(theatres);
        // Returns the list of theatres with an HTTP status of 200 (OK).
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }

    // Handles GET requests to retrieve a theatre by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
        // Attempts to fetch a theatre by ID from the service.
        Optional<Theatre> theatre = theatreService.getTheatreById(id);
        // If found, returns the theatre with status 200 (OK); otherwise, returns 404 (Not Found).
        return theatre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Handles GET requests to retrieve a theatre by its name.
    @GetMapping("/name/{theatreName}")
    public ResponseEntity<Theatre> getTheatreByName(@PathVariable String theatreName) {
        // Attempts to fetch a theatre by name from the service.
        Optional<Theatre> theatre = theatreService.getTheatreByName(theatreName);
        // If found, returns the theatre with status 200 (OK); otherwise, returns 404 (Not Found).
        return theatre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Handles POST requests to create a new theatre.
    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
        // Creates a new theatre using the service.
        Theatre createdTheatre = theatreService.createTheatre(theatre);
        // Returns the created theatre with status 201 (Created).
        return new ResponseEntity<>(createdTheatre, HttpStatus.CREATED);
    }

    // Handles PUT requests to update an existing theatre.
    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatreDetails) {
        // Attempts to update a theatre by ID with new details.
        Theatre updatedTheatre = theatreService.updateTheatre(id, theatreDetails);
        // If updated, returns the theatre with status 200 (OK); otherwise, returns 404 (Not Found).
        return updatedTheatre != null ? new ResponseEntity<>(updatedTheatre, HttpStatus.OK)
                                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Handles DELETE requests to remove a theatre by its ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
        // Attempts to delete a theatre by ID.
        return theatreService.deleteTheatre(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

