package project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project2.entity.HorseOwner;

public interface HorseOwnerRepo extends JpaRepository<HorseOwner, Integer> {
}
