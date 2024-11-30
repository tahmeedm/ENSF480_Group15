package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/early-screenings")
public class EarlyScreeningController {

    @Autowired
    private EarlyScreeningService earlyScreeningService;

    @GetMapping
    public ResponseEntity<List<EarlyScreening>> getAllEarlyScreenings() {
        List<EarlyScreening> earlyScreenings = earlyScreeningService.getAllEarlyScreenings();
        return new ResponseEntity<>(earlyScreenings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EarlyScreening> getEarlyScreeningById(@PathVariable Long id) {
        Optional<EarlyScreening> earlyScreening = earlyScreeningService.getEarlyScreeningById(id);
        return earlyScreening.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/early-date/{earlyDate}")
    public ResponseEntity<List<EarlyScreening>> getEarlyScreeningsByEarlyDate(@PathVariable String earlyDate) {
        List<EarlyScreening> earlyScreenings = earlyScreeningService.getEarlyScreeningsByEarlyDate(earlyDate);
        return new ResponseEntity<>(earlyScreenings, HttpStatus.OK);
    }

    @GetMapping("/percent-registered/{percent}")
    public ResponseEntity<List<EarlyScreening>> getEarlyScreeningsByPercentRegisteredGreaterThan(@PathVariable float percent) {
        List<EarlyScreening> earlyScreenings = earlyScreeningService.getEarlyScreeningsByPercentRegisteredGreaterThan(percent);
        return new ResponseEntity<>(earlyScreenings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EarlyScreening> createEarlyScreening(@RequestBody EarlyScreening earlyScreening) {
        EarlyScreening createdEarlyScreening = earlyScreeningService.createEarlyScreening(earlyScreening);
        return new ResponseEntity<>(createdEarlyScreening, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EarlyScreening> updateEarlyScreening(@PathVariable Long id, @RequestBody EarlyScreening earlyScreeningDetails) {
        EarlyScreening updatedEarlyScreening = earlyScreeningService.updateEarlyScreening(id, earlyScreeningDetails);
        return updatedEarlyScreening != null ? new ResponseEntity<>(updatedEarlyScreening, HttpStatus.OK)
                                             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEarlyScreening(@PathVariable Long id) {
        return earlyScreeningService.deleteEarlyScreening(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                                               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
