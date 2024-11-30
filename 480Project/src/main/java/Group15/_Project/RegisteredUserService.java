package Group15._Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public Optional<RegisteredUser> findByUsername(String username) {
        return registeredUserRepository.findByUsername(username);
    }

    public Optional<RegisteredUser> findByEmail(String email) {
        return registeredUserRepository.findByEmail(email);
    }

    public RegisteredUser save(RegisteredUser registeredUser) {
        return registeredUserRepository.save(registeredUser);
    }

    public void deleteById(Long id) {
        registeredUserRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return registeredUserRepository.findByUsername(username).isPresent();
    }

    public boolean existsByEmail(String email) {
        return registeredUserRepository.findByEmail(email).isPresent();
    }
}
