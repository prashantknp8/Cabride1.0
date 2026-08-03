package auth_service.service;


import auth_service.dto.AuthResponse;
import auth_service.dto.LoginRequest;
import auth_service.dto.RegisterRequest;
import auth_service.entity.User;
import auth_service.exception.ResourceAlreadyExistsException;
import auth_service.mapper.UserMapper;
import auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists.");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException("Phone number already exists.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = UserMapper.toEntity(request, encodedPassword);

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        throw new UnsupportedOperationException("Login not implemented yet.");

    }
}
