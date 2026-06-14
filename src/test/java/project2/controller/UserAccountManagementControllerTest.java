package project2.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import project2.dto.UserAccountResponse;
import project2.exception.ApiExceptionHandler;
import project2.service.UserAccountManagementService;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class UserAccountManagementControllerTest {

    private UserAccountManagementService service;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        service = mock(UserAccountManagementService.class);
        mockMvc = standaloneSetup(new UserAccountManagementController(service))
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void findAllReturnsAccountDataWithoutPassword() throws Exception {
        when(service.findAll(null)).thenReturn(List.of(
                new UserAccountResponse(
                        1, "HORSE_OWNER", "owner01", "Horse Owner", "owner@example.com", "0900000000"
                )
        ));

        mockMvc.perform(get("/api/admin/user-accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountType").value("HORSE_OWNER"))
                .andExpect(jsonPath("$[0].userName").value("owner01"))
                .andExpect(jsonPath("$[0].password").doesNotExist());
    }
}
