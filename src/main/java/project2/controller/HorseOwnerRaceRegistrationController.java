package project2.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2.dto.RaceRegistrationRequest;
import project2.dto.RaceRegistrationResponse;
import project2.service.RaceRegistrationService;

@RestController
@RequestMapping("/api/horse-owners/{horseOwnerId}/race-participations")
public class HorseOwnerRaceRegistrationController {

    private final RaceRegistrationService raceRegistrationService;

    public HorseOwnerRaceRegistrationController(RaceRegistrationService raceRegistrationService) {
        this.raceRegistrationService = raceRegistrationService;
    }

    @PostMapping
    public ResponseEntity<RaceRegistrationResponse> register(
            @PathVariable Integer horseOwnerId,
            @Valid @RequestBody RaceRegistrationRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(raceRegistrationService.register(horseOwnerId, request));
    }
}
