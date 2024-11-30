package Group15._Project;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByUsername(String username);
    Optional<RegisteredUser> findByEmail(String email);
}

