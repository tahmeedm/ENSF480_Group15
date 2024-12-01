package Group15._Project;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/users")
public class RegisteredUserController {

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

    @Autowired
    private RegisteredUserService registeredUserService;

    /**
     * Handles GET requests to retrieve a user by their username.
     *
     * @param username the username to search for
     * @return a ResponseEntity containing the user if found, or a 404 status code if not found
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<RegisteredUser> getUserByUsername(@PathVariable String username) {
        Optional<RegisteredUser> user = registeredUserService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/signIn/{username}/{password}")
    public ResponseEntity<RegisteredUser> signIn(@PathVariable String username, @PathVariable String password) {
        Optional<RegisteredUser> user = registeredUserService.findByUsernameAndPassword(username, password);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Handles GET requests to retrieve a user by their email.
     *
     * @param email the email to search for
     * @return a ResponseEntity containing the user if found, or a 404 status code if not found
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<RegisteredUser> getUserByEmail(@PathVariable String email) {
        Optional<RegisteredUser> user = registeredUserService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request payload");
    }

    /**
     * Handles POST requests to create a new user.
     *
     * @param registeredUser the user details to save
     * @return a ResponseEntity containing the saved user if successful, or a 400 status code if the username or email already exists
     */
    @PostMapping("/register")
    public ResponseEntity<RegisteredUser> createUser(@RequestBody RegisteredUser registeredUser) {
        System.out.println("User info: " + registeredUser);
        if (registeredUserService.existsByUsername(registeredUser.getUsername())) {
            System.out.println("Username already taken");
            return ResponseEntity.badRequest().body(null); // Username already taken
        }
        if (registeredUserService.existsByEmail(registeredUser.getEmail())) {
            System.out.println("Email already taken");
            return ResponseEntity.badRequest().body(null); // Email already taken
        }
        RegisteredUser savedUser = registeredUserService.save(registeredUser);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Handles DELETE requests to remove a user by their ID.
     *
     * @param id the ID of the user to remove
     * @return a ResponseEntity with a 204 status code if the user is deleted, or a 404 status code if the user is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<RegisteredUser> user = registeredUserService.findByUsername(id.toString());
        if (user.isPresent()) {
            registeredUserService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
