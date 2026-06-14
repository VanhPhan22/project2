package project2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project2.dto.RegisterRequest;
import project2.dto.RegisterResponse;
import project2.entity.Spectator;
import project2.exception.DuplicateAccountException;
import project2.repository.SpectatorRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private SpectatorRepo spectatorRepo;

    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        authService = new AuthService(spectatorRepo, passwordEncoder);
    }

    @Test
    void registerHashesPasswordAndNormalizesEmail() {
        when(spectatorRepo.saveAndFlush(any(Spectator.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RegisterResponse response = authService.register(
                new RegisterRequest("spectator_01", "USER@Example.COM ", "password123")
        );

        ArgumentCaptor<Spectator> captor = ArgumentCaptor.forClass(Spectator.class);
        verify(spectatorRepo).saveAndFlush(captor.capture());
        Spectator savedSpectator = captor.getValue();

        assertEquals("user@example.com", savedSpectator.getEmail());
        assertNotEquals("password123", savedSpectator.getPassword());
        assertTrue(passwordEncoder.matches("password123", savedSpectator.getPassword()));
        assertEquals("user@example.com", response.email());
        assertEquals("SPECTATOR", response.role());
    }

    @Test
    void registerRejectsDuplicateUsername() {
        when(spectatorRepo.existsByUserNameIgnoreCase("spectator_01")).thenReturn(true);

        assertThrows(
                DuplicateAccountException.class,
                () -> authService.register(
                        new RegisterRequest("spectator_01", "user@example.com", "password123")
                )
        );

        verify(spectatorRepo, never()).saveAndFlush(any(Spectator.class));
    }

    @Test
    void registerRejectsDuplicateEmail() {
        when(spectatorRepo.existsByEmailIgnoreCase("user@example.com")).thenReturn(true);

        assertThrows(
                DuplicateAccountException.class,
                () -> authService.register(
                        new RegisterRequest("spectator_01", "USER@example.com", "password123")
                )
        );

        verify(spectatorRepo, never()).saveAndFlush(any(Spectator.class));
    }
}
