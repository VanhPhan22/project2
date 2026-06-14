package project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project2.entity.RaceParticipation;

public interface RaceParticipationRepo extends JpaRepository<RaceParticipation, Integer> {
    boolean existsByHorse_IdAndRaceSchedule_Id(Integer horseId, Integer raceScheduleId);

    boolean existsByRaceSchedule_IdAndLaneNumber(Integer raceScheduleId, Integer laneNumber);
}
