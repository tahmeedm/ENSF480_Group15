package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EarlyScreeningService {

    @Autowired
    private EarlyScreeningRepository earlyScreeningRepository;

    public List<EarlyScreening> getAllEarlyScreenings() {
        return earlyScreeningRepository.findAll();
    }

    public Optional<EarlyScreening> getEarlyScreeningById(Long id) {
        return earlyScreeningRepository.findById(id);
    }

    public List<EarlyScreening> getEarlyScreeningsByEarlyDate(String earlyDate) {
        return earlyScreeningRepository.findByEarlyDate(earlyDate);
    }

    public List<EarlyScreening> getEarlyScreeningsByPercentRegisteredGreaterThan(float percent) {
        return earlyScreeningRepository.findByPercentRegisteredGreaterThan(percent);
    }

    public EarlyScreening createEarlyScreening(EarlyScreening earlyScreening) {
        return earlyScreeningRepository.save(earlyScreening);
    }

    public EarlyScreening updateEarlyScreening(Long id, EarlyScreening earlyScreeningDetails) {
        if (earlyScreeningRepository.existsById(id)) {
            earlyScreeningDetails.setId(id);
            return earlyScreeningRepository.save(earlyScreeningDetails);
        }
        return null;
    }

    public boolean deleteEarlyScreening(Long id) {
        if (earlyScreeningRepository.existsById(id)) {
            earlyScreeningRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
