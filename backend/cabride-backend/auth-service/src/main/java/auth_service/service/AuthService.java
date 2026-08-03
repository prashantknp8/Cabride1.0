package auth_service.service;

import auth_service.dto.AuthResponse;
import auth_service.dto.LoginRequest;
import auth_service.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
