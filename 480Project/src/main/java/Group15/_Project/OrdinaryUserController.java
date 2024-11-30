package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ordinary-users")
public class OrdinaryUserController {

    @Autowired
    private OrdinaryUserService ordinaryUserService;

    @GetMapping("/admin-fee-expiration/{expirationDate}")
    public ResponseEntity<OrdinaryUser> getUserByAdminFeeExpirationDate(@PathVariable String expirationDate) {
        Optional<OrdinaryUser> user = ordinaryUserService.findByAdminFeeExipirationDate(expirationDate);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/has-admin-fee/{hasAdminFee}")
    public ResponseEntity<OrdinaryUser> getUserByHasAdminFee(@PathVariable boolean hasAdminFee) {
        Optional<OrdinaryUser> user = ordinaryUserService.findByHasAdminFee(hasAdminFee);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<OrdinaryUser> getUserByEmail(@PathVariable String email) {
        Optional<OrdinaryUser> user = ordinaryUserService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<OrdinaryUser> createUser(@RequestBody OrdinaryUser ordinaryUser) {
        if (ordinaryUserService.existsByEmail(ordinaryUser.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }
        OrdinaryUser savedUser = ordinaryUserService.save(ordinaryUser);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<OrdinaryUser> user = ordinaryUserService.findByEmail(id.toString());
        if (user.isPresent()) {
            ordinaryUserService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
