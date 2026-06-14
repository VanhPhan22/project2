package project2.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project2.dto.RegisterRequest;
import project2.dto.RegisterResponse;
import project2.entity.Spectator;
import project2.exception.DuplicateAccountException;
import project2.repository.SpectatorRepo;

import java.util.Locale;

@Service
public class AuthService {

    private final SpectatorRepo spectatorRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(SpectatorRepo spectatorRepo, PasswordEncoder passwordEncoder) {
        this.spectatorRepo = spectatorRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        String userName = request.userName().trim();
        String email = request.email().trim().toLowerCase(Locale.ROOT);

        if (spectatorRepo.existsByUserNameIgnoreCase(userName)) {
            throw new DuplicateAccountException("Username is already registered");
        }
        if (spectatorRepo.existsByEmailIgnoreCase(email)) {
            throw new DuplicateAccountException("Email is already registered");
        }

        Spectator spectator = new Spectator(userName, email, passwordEncoder.encode(request.password()));
        try {
            Spectator savedSpectator = spectatorRepo.saveAndFlush(spectator);
            return new RegisterResponse(
                    savedSpectator.getId(),
                    savedSpectator.getUserName(),
                    savedSpectator.getEmail(),
                    "SPECTATOR"
            );
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateAccountException("Username or email is already registered");
        }
    }
}
