package Group15._Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    
    List<Screening> findByTheatre(Theatre theatre);

    //find by theatre id
    // @Query("SELECT s FROM Screening s JOIN FETCH s.screeningSeatList WHERE s.theatre.id = :theatreId")
    List<Screening> findByTheatreId(Theatre theatre);

    List<Screening> findByMovie(Movie movie);

    List<Screening> findByMovieAndScreenDate(Movie movie, String screenDate);

    List<Screening> findByScreenDate(String screenDate);
}

