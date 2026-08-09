package auth_service.controller;

import auth_service.dto.LoginRequest;
import auth_service.dto.RegisterRequest;
import auth_service.dto.response.ApiResponse;
import auth_service.dto.response.AuthResponse;
import auth_service.dto.response.UserResponse;
import auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse user= authService.register(request);
        ApiResponse<UserResponse> response=ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(user)
                .timestamp(LocalDateTime.now())
                .build();


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request
            ){
        AuthResponse authResponse=authService.login(request);

        ApiResponse<AuthResponse> response=ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Login Successful")
                .data(authResponse)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }


}
