package Group15._Project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinaryUserRepository extends JpaRepository<OrdinaryUser, Long> {
    Optional<OrdinaryUser> findByAdminFeeExpirationDate(String expirationDate);
    Optional<OrdinaryUser> findByHasAdminFee(boolean hasAdminFee);
    Optional<OrdinaryUser> findByEmail(String email);
}

