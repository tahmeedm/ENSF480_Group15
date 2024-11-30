package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrdinaryUserService {

    @Autowired
    private OrdinaryUserRepository ordinaryUserRepository;

    public Optional<OrdinaryUser> findByAdminFeeExipirationDate(String expirationDate) {
        return ordinaryUserRepository.findByAdminFeeExipirationDate(expirationDate);
    }

    public Optional<OrdinaryUser> findByHasAdminFee(boolean hasAdminFee) {
        return ordinaryUserRepository.findByHasAdminFee(hasAdminFee);
    }

    public Optional<OrdinaryUser> findByEmail(String email) {
        return ordinaryUserRepository.findByEmail(email);
    }

    public OrdinaryUser save(OrdinaryUser ordinaryUser) {
        return ordinaryUserRepository.save(ordinaryUser);
    }

    public void deleteById(Long id) {
        ordinaryUserRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return ordinaryUserRepository.findByEmail(email).isPresent();
    }
}
