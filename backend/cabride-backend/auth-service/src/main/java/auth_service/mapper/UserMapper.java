package auth_service.mapper;

import auth_service.dto.RegisterRequest;
import auth_service.entity.User;

import java.time.LocalDateTime;

public final class UserMapper {

    private UserMapper() {}

    public static User toEntity(RegisterRequest request, String encodedPassword) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(encodedPassword);
        user.setRole(request.getRole());

        user.setEmailVerified(false);
        user.setAccountNonLocked(true);
        user.setFailedAttempts(0);
        user.setLockTime(null);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }
}