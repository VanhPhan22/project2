package project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project2.entity.Spectator;

public interface SpectatorRepo extends JpaRepository<Spectator, Integer> {
    boolean existsByUserNameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);
}
