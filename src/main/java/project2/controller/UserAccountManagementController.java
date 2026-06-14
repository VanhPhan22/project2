package project2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project2.dto.UserAccountResponse;
import project2.enums.UserAccountType;
import project2.service.UserAccountManagementService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user-accounts")
public class UserAccountManagementController {

    private final UserAccountManagementService userAccountManagementService;

    public UserAccountManagementController(UserAccountManagementService userAccountManagementService) {
        this.userAccountManagementService = userAccountManagementService;
    }

    @GetMapping
    public List<UserAccountResponse> findAll(
            @RequestParam(required = false) UserAccountType accountType
    ) {
        return userAccountManagementService.findAll(accountType);
    }

    @GetMapping("/{accountType}/{id}")
    public UserAccountResponse findById(
            @PathVariable UserAccountType accountType,
            @PathVariable Integer id
    ) {
        return userAccountManagementService.findById(accountType, id);
    }

    @DeleteMapping("/{accountType}/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UserAccountType accountType,
            @PathVariable Integer id
    ) {
        userAccountManagementService.delete(accountType, id);
        return ResponseEntity.noContent().build();
    }
}
