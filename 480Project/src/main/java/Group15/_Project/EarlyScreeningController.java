package Group15._Project;

import java.util.List;
import java.util.Optional;

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
import org.springframework.context.annotation.Configuration;

// Annotation to define this class as a Rest Controller
@RestController
// Base URL mapping for all requests handled by this controller
@RequestMapping("/early-screenings")
public class EarlyScreeningController {

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

    // Inject the EarlyScreeningService to handle business logic
    @Autowired
    private EarlyScreeningService earlyScreeningService;

    // Endpoint to retrieve all early screenings
    @GetMapping
    public ResponseEntity<List<EarlyScreening>> getAllEarlyScreenings() {
        // Call service to get all early screenings
        List<EarlyScreening> earlyScreenings = earlyScreeningService.getAllEarlyScreenings();
        // Return the list of early screenings with an HTTP OK status
        return new ResponseEntity<>(earlyScreenings, HttpStatus.OK);
    }

    // Endpoint to retrieve a specific early screening by ID
    @GetMapping("/{id}")
    public ResponseEntity<EarlyScreening> getEarlyScreeningById(@PathVariable Long id) {
        // Use service to retrieve early screening by ID
        Optional<EarlyScreening> earlyScreening = earlyScreeningService.getEarlyScreeningById(id);
        // If found, return the early screening with an HTTP OK status, else return HTTP NOT FOUND
        return earlyScreening.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to retrieve early screenings by early date
    @GetMapping("/early-date/{earlyDate}")
    public ResponseEntity<List<EarlyScreening>> getEarlyScreeningsByEarlyDate(@PathVariable String earlyDate) {
        // Use service to get screenings by early date
        List<EarlyScreening> earlyScreenings = earlyScreeningService.getEarlyScreeningsByEarlyDate(earlyDate);
        // Return the list with an HTTP OK status
        return new ResponseEntity<>(earlyScreenings, HttpStatus.OK);
    }

    // Endpoint to retrieve early screenings with registration percent greater than specified value
    @GetMapping("/percent-registered/{percent}")
    public ResponseEntity<List<EarlyScreening>> getEarlyScreeningsByPercentRegisteredGreaterThan(@PathVariable float percent) {
        // Call service to get screenings by percent registered
        List<EarlyScreening> earlyScreenings = earlyScreeningService.getEarlyScreeningsByPercentRegisteredGreaterThan(percent);
        // Return the list with an HTTP OK status
        return new ResponseEntity<>(earlyScreenings, HttpStatus.OK);
    }

    // Endpoint to create a new early screening
    @PostMapping
    public ResponseEntity<EarlyScreening> createEarlyScreening(@RequestBody EarlyScreening earlyScreening) {
        // Use service to create a new early screening
        EarlyScreening createdEarlyScreening = earlyScreeningService.createEarlyScreening(earlyScreening);
        // Return the created screening with an HTTP CREATED status
        return new ResponseEntity<>(createdEarlyScreening, HttpStatus.CREATED);
    }

    // Endpoint to update an existing early screening by ID
    @PutMapping("/{id}")
    public ResponseEntity<EarlyScreening> updateEarlyScreening(@PathVariable Long id, @RequestBody EarlyScreening earlyScreeningDetails) {
        // Use service to update the early screening
        EarlyScreening updatedEarlyScreening = earlyScreeningService.updateEarlyScreening(id, earlyScreeningDetails);
        // If update is successful, return the updated screening with an HTTP OK status, else HTTP NOT FOUND
        return updatedEarlyScreening != null ? new ResponseEntity<>(updatedEarlyScreening, HttpStatus.OK)
                                             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete an early screening by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEarlyScreening(@PathVariable Long id) {
        // Use service to delete the early screening
        return earlyScreeningService.deleteEarlyScreening(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                                               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

