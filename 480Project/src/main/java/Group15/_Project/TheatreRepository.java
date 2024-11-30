package Group15._Project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    Optional<Theatre> findByTheatreName(String theatreName);
}

