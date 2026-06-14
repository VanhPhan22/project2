package project2.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project2.dto.RaceRegistrationRequest;
import project2.dto.RaceRegistrationResponse;
import project2.enums.RaceParticipationStatus;
import project2.exception.ApiExceptionHandler;
import project2.service.RaceRegistrationService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class HorseOwnerRaceRegistrationControllerTest {

    private RaceRegistrationService service;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        service = mock(RaceRegistrationService.class);
        mockMvc = standaloneSetup(new HorseOwnerRaceRegistrationController(service))
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void registerReturnsCreatedParticipation() throws Exception {
        when(service.register(eq(1), any(RaceRegistrationRequest.class))).thenReturn(
                new RaceRegistrationResponse(
                        10, RaceParticipationStatus.PENDING, 2, 1, 3, "Storm",
                        4, "Race 1", 5, "Summer Cup", 6, "Jockey"
                )
        );

        mockMvc.perform(post("/api/horse-owners/1/race-participations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"horseId":3,"raceScheduleId":4,"jockeyId":6,"laneNumber":2}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.horseName").value("Storm"))
                .andExpect(jsonPath("$.tournamentName").value("Summer Cup"));
    }

    @Test
    void registerRejectsInvalidRequest() throws Exception {
        mockMvc.perform(post("/api/horse-owners/1/race-participations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"horseId":0,"raceScheduleId":null,"jockeyId":-1,"laneNumber":0}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"));

        verifyNoInteractions(service);
    }
}
