package auth_service.entity;

import auth_service.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String password;

    private Role role;

    private boolean emailVerified;

    private boolean accountNonLocked=true;

    private int failedAttempts;

    private LocalDateTime lockTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
