package Group15._Project;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrdinaryUserRepository extends JpaRepository<OrdinaryUser, Long> {
    Optional<OrdinaryUser> findByAdminFeeExipirationDate(String expirationDate);
    Optional<OrdinaryUser> findByHasAdminFee(boolean hasAdminFee);
    Optional<OrdinaryUser> findByEmail(String email);
}

