package project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project2.entity.RaceReferee;

public interface RaceRefereeRepo extends JpaRepository<RaceReferee, Integer> {
}
