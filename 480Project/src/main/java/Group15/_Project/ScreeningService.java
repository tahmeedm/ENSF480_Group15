package Group15._Project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScreeningService {

    @Autowired
    private ScreeningRepository screeningRepository;

    public List<Screening> findByTheatre(Theatre theatre) {
        return screeningRepository.findByTheatre(theatre);
    }

    public List<Screening> findByTheatreId(Theatre theatre) {
        return screeningRepository.findByTheatreId(theatre);
    }

    public List<Screening> findByMovie(Movie movie) {
        return screeningRepository.findByMovie(movie);
    }

    public List<Screening> findByScreenDate(String screenDate) {
        return screeningRepository.findByScreenDate(screenDate);
    }

    public Screening save(Screening screening) {
        return screeningRepository.save(screening);
    }

    public void deleteById(Long id) {
        screeningRepository.deleteById(id);
    }
}
