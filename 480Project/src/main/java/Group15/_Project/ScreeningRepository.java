package Group15._Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findByTheatre(Theatre theatre);

    List<Screening> findByMovie(Movie movie);

    List<Screening> findByScreenDate(String screenDate);
}

