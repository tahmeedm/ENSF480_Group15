package Group15._Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Optional<Theatre> getTheatreById(Long id) {
        return theatreRepository.findById(id);
    }

    public Optional<Theatre> getTheatreByName(String theatreName) {
        return theatreRepository.findByTheatreName(theatreName);
    }

    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public Theatre updateTheatre(Long id, Theatre theatreDetails) {
        if (theatreRepository.existsById(id)) {
            theatreDetails.setId(id);
            return theatreRepository.save(theatreDetails);
        }
        return null;
    }

    public boolean deleteTheatre(Long id) {
        if (theatreRepository.existsById(id)) {
            theatreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
