package project2.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project2.dto.RaceRegistrationRequest;
import project2.dto.RaceRegistrationResponse;
import project2.entity.Horse;
import project2.entity.HorseOwner;
import project2.entity.Jockey;
import project2.entity.RaceParticipation;
import project2.entity.RaceSchedule;
import project2.entity.Tournament;
import project2.enums.RaceParticipationStatus;
import project2.enums.RaceScheduleStatus;
import project2.exception.BusinessRuleException;
import project2.exception.ResourceNotFoundException;
import project2.repository.HorseOwnerRepo;
import project2.repository.HorseRepo;
import project2.repository.JockeyRepo;
import project2.repository.RaceParticipationRepo;
import project2.repository.RaceScheduleRepo;

import java.time.LocalDate;
import java.util.Locale;

@Service
public class RaceRegistrationService {

    private final HorseOwnerRepo horseOwnerRepo;
    private final HorseRepo horseRepo;
    private final RaceScheduleRepo raceScheduleRepo;
    private final JockeyRepo jockeyRepo;
    private final RaceParticipationRepo raceParticipationRepo;

    public RaceRegistrationService(
            HorseOwnerRepo horseOwnerRepo,
            HorseRepo horseRepo,
            RaceScheduleRepo raceScheduleRepo,
            JockeyRepo jockeyRepo,
            RaceParticipationRepo raceParticipationRepo
    ) {
        this.horseOwnerRepo = horseOwnerRepo;
        this.horseRepo = horseRepo;
        this.raceScheduleRepo = raceScheduleRepo;
        this.jockeyRepo = jockeyRepo;
        this.raceParticipationRepo = raceParticipationRepo;
    }

    @Transactional
    public RaceRegistrationResponse register(Integer horseOwnerId, RaceRegistrationRequest request) {
        HorseOwner horseOwner = horseOwnerRepo.findById(horseOwnerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Horse owner with ID " + horseOwnerId + " was not found"
                ));
        Horse horse = horseRepo.findById(request.horseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Horse with ID " + request.horseId() + " was not found"
                ));
        RaceSchedule raceSchedule = raceScheduleRepo.findById(request.raceScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Race schedule with ID " + request.raceScheduleId() + " was not found"
                ));
        Jockey jockey = jockeyRepo.findById(request.jockeyId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Jockey with ID " + request.jockeyId() + " was not found"
                ));

        validateRegistration(horseOwner, horse, raceSchedule, request);

        RaceParticipation participation = new RaceParticipation(
                RaceParticipationStatus.PENDING,
                request.laneNumber()
        );
        participation.setHorse(horse);
        participation.setRaceSchedule(raceSchedule);
        participation.setJockey(jockey);

        try {
            return toResponse(horseOwner, raceParticipationRepo.saveAndFlush(participation));
        } catch (DataIntegrityViolationException exception) {
            throw new BusinessRuleException(
                    "The horse or lane number is already registered for this race schedule"
            );
        }
    }

    private void validateRegistration(
            HorseOwner horseOwner,
            Horse horse,
            RaceSchedule raceSchedule,
            RaceRegistrationRequest request
    ) {
        if (horse.getHorseOwner() == null || !horseOwner.getId().equals(horse.getHorseOwner().getId())) {
            throw new BusinessRuleException("The horse does not belong to this horse owner");
        }
        if (!isHealthy(horse.getHealthStatus())) {
            throw new BusinessRuleException("The horse is not healthy enough to participate");
        }
        if (raceSchedule.getTournament() == null) {
            throw new BusinessRuleException("The race schedule is not assigned to a tournament");
        }
        if (raceSchedule.getStatus() != RaceScheduleStatus.PENDING) {
            throw new BusinessRuleException("The race schedule is not accepting registrations");
        }
        if (raceSchedule.getRaceDate().isBefore(LocalDate.now())) {
            throw new BusinessRuleException("Cannot register for a past race schedule");
        }
        if (raceParticipationRepo.existsByHorse_IdAndRaceSchedule_Id(horse.getId(), raceSchedule.getId())) {
            throw new BusinessRuleException("The horse is already registered for this race schedule");
        }
        if (raceParticipationRepo.existsByRaceSchedule_IdAndLaneNumber(
                raceSchedule.getId(),
                request.laneNumber()
        )) {
            throw new BusinessRuleException("The lane number is already assigned in this race schedule");
        }
    }

    private boolean isHealthy(String healthStatus) {
        if (healthStatus == null) {
            return false;
        }
        String normalizedStatus = healthStatus.trim().toLowerCase(Locale.ROOT);
        return normalizedStatus.equals("healthy") || normalizedStatus.equals("khỏe mạnh");
    }

    private RaceRegistrationResponse toResponse(HorseOwner horseOwner, RaceParticipation participation) {
        Horse horse = participation.getHorse();
        RaceSchedule raceSchedule = participation.getRaceSchedule();
        Tournament tournament = raceSchedule.getTournament();
        Jockey jockey = participation.getJockey();

        return new RaceRegistrationResponse(
                participation.getId(),
                participation.getStatus(),
                participation.getLaneNumber(),
                horseOwner.getId(),
                horse.getId(),
                horse.getName(),
                raceSchedule.getId(),
                raceSchedule.getName(),
                tournament.getId(),
                tournament.getName(),
                jockey.getId(),
                jockey.getFullName()
        );
    }
}
