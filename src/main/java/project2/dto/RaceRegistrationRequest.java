package project2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RaceRegistrationRequest(
        @NotNull(message = "Horse ID is required")
        @Positive(message = "Horse ID must be positive")
        Integer horseId,

        @NotNull(message = "Race schedule ID is required")
        @Positive(message = "Race schedule ID must be positive")
        Integer raceScheduleId,

        @NotNull(message = "Jockey ID is required")
        @Positive(message = "Jockey ID must be positive")
        Integer jockeyId,

        @NotNull(message = "Lane number is required")
        @Positive(message = "Lane number must be positive")
        Integer laneNumber
) {
}
