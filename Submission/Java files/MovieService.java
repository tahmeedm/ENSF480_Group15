package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    public List<Movie> findByReleaseDate(String releaseDate) {
        return movieRepository.findByReleaseDate(releaseDate);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return movieRepository.findByName(name).isPresent();
    }
}
