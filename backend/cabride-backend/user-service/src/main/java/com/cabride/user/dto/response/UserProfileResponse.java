package com.cabride.user.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserProfileResponse {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profileImageUrl;

}
