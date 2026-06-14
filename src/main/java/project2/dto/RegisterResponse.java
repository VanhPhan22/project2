package project2.dto;

public record RegisterResponse(
        Integer id,
        String userName,
        String email,
        String role
) {
}
