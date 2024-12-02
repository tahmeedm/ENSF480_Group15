package Group15._Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EarlyScreeningRepository extends JpaRepository<EarlyScreening, Long> {
    List<EarlyScreening> findByEarlyDate(String earlyDate);
    List<EarlyScreening> findByPercentRegisteredGreaterThan(float percent);
}

