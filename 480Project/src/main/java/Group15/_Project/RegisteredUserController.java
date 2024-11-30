package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class RegisteredUserController {

    @Autowired
    private RegisteredUserService registeredUserService;

    @GetMapping("/username/{username}")
    public ResponseEntity<RegisteredUser> getUserByUsername(@PathVariable String username) {
        Optional<RegisteredUser> user = registeredUserService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<RegisteredUser> getUserByEmail(@PathVariable String email) {
        Optional<RegisteredUser> user = registeredUserService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredUser> createUser(@RequestBody RegisteredUser registeredUser) {
        if (registeredUserService.existsByUsername(registeredUser.getUsername())) {
            return ResponseEntity.badRequest().body(null); // Username already taken
        }
        if (registeredUserService.existsByEmail(registeredUser.getEmail())) {
            return ResponseEntity.badRequest().body(null); // Email already taken
        }
        RegisteredUser savedUser = registeredUserService.save(registeredUser);
        return ResponseEntity.ok(savedUser);
    }

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
