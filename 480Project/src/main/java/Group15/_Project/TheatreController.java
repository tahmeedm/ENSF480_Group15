package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return new ResponseEntity<>(theatres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreService.getTheatreById(id);
        return theatre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{theatreName}")
    public ResponseEntity<Theatre> getTheatreByName(@PathVariable String theatreName) {
        Optional<Theatre> theatre = theatreService.getTheatreByName(theatreName);
        return theatre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
        Theatre createdTheatre = theatreService.createTheatre(theatre);
        return new ResponseEntity<>(createdTheatre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatreDetails) {
        Theatre updatedTheatre = theatreService.updateTheatre(id, theatreDetails);
        return updatedTheatre != null ? new ResponseEntity<>(updatedTheatre, HttpStatus.OK)
                                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
        return theatreService.deleteTheatre(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
