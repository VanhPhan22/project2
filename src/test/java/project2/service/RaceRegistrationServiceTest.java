package project2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
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
import project2.repository.HorseOwnerRepo;
import project2.repository.HorseRepo;
import project2.repository.JockeyRepo;
import project2.repository.RaceParticipationRepo;
import project2.repository.RaceScheduleRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RaceRegistrationServiceTest {

    @Mock private HorseOwnerRepo horseOwnerRepo;
    @Mock private HorseRepo horseRepo;
    @Mock private RaceScheduleRepo raceScheduleRepo;
    @Mock private JockeyRepo jockeyRepo;
    @Mock private RaceParticipationRepo raceParticipationRepo;

    private RaceRegistrationService service;
    private HorseOwner owner;
    private Horse horse;
    private RaceSchedule schedule;
    private Jockey jockey;
    private RaceRegistrationRequest request;

    @BeforeEach
    void setUp() {
        service = new RaceRegistrationService(
                horseOwnerRepo, horseRepo, raceScheduleRepo, jockeyRepo, raceParticipationRepo
        );

        owner = new HorseOwner("Owner", "owner01", "0900000000", "password", "owner@example.com");
        horse = new Horse("Storm", 4, "Thoroughbred", "Khỏe mạnh");
        jockey = new Jockey("Jockey", "jockey01", 25, 5, "0911111111", "password");
        Tournament tournament = new Tournament(
                "Summer Cup", "HCMC", LocalDate.now(), LocalDate.now().plusDays(10)
        );
        schedule = new RaceSchedule(
                "Race 1", LocalDate.now().plusDays(5), "HCMC", RaceScheduleStatus.PENDING,
                LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(5).plusHours(1)
        );

        setId(owner, 1);
        setId(horse, 2);
        setId(jockey, 3);
        setId(tournament, 4);
        setId(schedule, 5);
        horse.setHorseOwner(owner);
        schedule.setTournament(tournament);
        request = new RaceRegistrationRequest(2, 5, 3, 7);

        when(horseOwnerRepo.findById(1)).thenReturn(Optional.of(owner));
        when(horseRepo.findById(2)).thenReturn(Optional.of(horse));
        when(raceScheduleRepo.findById(5)).thenReturn(Optional.of(schedule));
        when(jockeyRepo.findById(3)).thenReturn(Optional.of(jockey));
    }

    @Test
    void registerCreatesPendingParticipationWithRelationships() {
        when(raceParticipationRepo.saveAndFlush(any(RaceParticipation.class)))
                .thenAnswer(invocation -> {
                    RaceParticipation participation = invocation.getArgument(0);
                    setId(participation, 6);
                    return participation;
                });

        RaceRegistrationResponse response = service.register(1, request);

        assertEquals(6, response.id());
        assertEquals(RaceParticipationStatus.PENDING, response.status());
        assertEquals(2, response.horseId());
        assertEquals(5, response.raceScheduleId());
        assertEquals(4, response.tournamentId());
        verify(raceParticipationRepo).saveAndFlush(any(RaceParticipation.class));
    }

    @Test
    void registerRejectsHorseOwnedBySomeoneElse() {
        HorseOwner anotherOwner = new HorseOwner("Other", "other", "0922222222", "password", "other@example.com");
        setId(anotherOwner, 9);
        horse.setHorseOwner(anotherOwner);

        assertThrows(BusinessRuleException.class, () -> service.register(1, request));

        verify(raceParticipationRepo, never()).saveAndFlush(any(RaceParticipation.class));
    }

    @Test
    void registerRejectsDuplicateHorseRegistration() {
        when(raceParticipationRepo.existsByHorse_IdAndRaceSchedule_Id(2, 5)).thenReturn(true);

        assertThrows(BusinessRuleException.class, () -> service.register(1, request));

        verify(raceParticipationRepo, never()).saveAndFlush(any(RaceParticipation.class));
    }

    private void setId(Object entity, Integer id) {
        ReflectionTestUtils.setField(entity, "id", id);
    }
}
