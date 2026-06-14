package project2.dto;

public record UserAccountResponse(
        Integer id,
        String accountType,
        String userName,
        String fullName,
        String email,
        String phone
) {
}
