/**
 * This class provides endpoints for managing Ordinary Users.
 *
 * @author Group 15
 */
package Group15._Project;

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

/**
 * This class provides endpoints for managing Ordinary Users.
 */
@RestController
@RequestMapping("/ordinary-users")
public class OrdinaryUserController {

    /**
     * This injects the OrdinaryUserService into this class.
     */
    @Autowired
    private OrdinaryUserService ordinaryUserService;

    /**
     * This endpoint returns an OrdinaryUser based on a given admin fee expiration date.
     *
     * @param expirationDate The admin fee expiration date to search for.
     * @return The OrdinaryUser with the specified admin fee expiration date or a 404 if no such user exists.
     */
    @GetMapping("/admin-fee-expiration/{expirationDate}")
    public ResponseEntity<OrdinaryUser> getUserByAdminFeeExpirationDate(@PathVariable String expirationDate) {
        /**
         * This calls the findByAdminFeeExpirationDate method in the OrdinaryUserService class.
         */
        Optional<OrdinaryUser> user = ordinaryUserService.findByAdminFeeExpirationDate(expirationDate);

        /**
         * If the user exists, return it. Otherwise, return a 404.
         */
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This endpoint returns an OrdinaryUser based on a given has admin fee value.
     *
     * @param hasAdminFee The value of has admin fee to search for.
     * @return The OrdinaryUser with the specified has admin fee value or a 404 if no such user exists.
     */
    @GetMapping("/has-admin-fee/{hasAdminFee}")
    public ResponseEntity<OrdinaryUser> getUserByHasAdminFee(@PathVariable boolean hasAdminFee) {
        /**
         * This calls the findByHasAdminFee method in the OrdinaryUserService class.
         */
        Optional<OrdinaryUser> user = ordinaryUserService.findByHasAdminFee(hasAdminFee);

        /**
         * If the user exists, return it. Otherwise, return a 404.
         */
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This endpoint returns an OrdinaryUser based on a given email.
     *
     * @param email The email to search for.
     * @return The OrdinaryUser with the specified email or a 404 if no such user exists.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<OrdinaryUser> getUserByEmail(@PathVariable String email) {
        /**
         * This calls the findByEmail method in the OrdinaryUserService class.
         */
        Optional<OrdinaryUser> user = ordinaryUserService.findByEmail(email);

        /**
         * If the user exists, return it. Otherwise, return a 404.
         */
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This endpoint creates a new OrdinaryUser.
     *
     * @param ordinaryUser The OrdinaryUser to create.
     * @return The created OrdinaryUser or a 400 if the email already exists.
     */
    @PostMapping("/register")
    public ResponseEntity<OrdinaryUser> createUser(@RequestBody OrdinaryUser ordinaryUser) {
        /**
         * This checks if the email already exists in the database.
         */
        if (ordinaryUserService.existsByEmail(ordinaryUser.getEmail())) {
            return ResponseEntity.badRequest().body(null);
        }

        /**
         * This calls the save method in the OrdinaryUserService class.
         */
        OrdinaryUser savedUser = ordinaryUserService.save(ordinaryUser);

        /**
         * Return the created user.
         */
        return ResponseEntity.ok(savedUser);
    }

    /**
     * This endpoint deletes an OrdinaryUser based on a given id.
     *
     * @param id The id of the OrdinaryUser to delete.
     * @return A 204 if the user is deleted or a 404 if the user does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        /**
         * This calls the findByEmail method in the OrdinaryUserService class.
         */
        Optional<OrdinaryUser> user = ordinaryUserService.findByEmail(id.toString());

        /**
         * If the user exists, delete it. Otherwise, return a 404.
         */
        if (user.isPresent()) {
            ordinaryUserService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

