package project2.dto;

import project2.enums.RaceParticipationStatus;

public record RaceRegistrationResponse(
        Integer id,
        RaceParticipationStatus status,
        Integer laneNumber,
        Integer horseOwnerId,
        Integer horseId,
        String horseName,
        Integer raceScheduleId,
        String raceScheduleName,
        Integer tournamentId,
        String tournamentName,
        Integer jockeyId,
        String jockeyName
) {
}
