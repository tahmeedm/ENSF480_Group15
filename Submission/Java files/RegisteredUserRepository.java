package Group15._Project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByUsername(String username);

    Optional<RegisteredUser> findByUsernameAndPassword(String username, String password);

    Optional<RegisteredUser> findByEmail(String email);

    
}

