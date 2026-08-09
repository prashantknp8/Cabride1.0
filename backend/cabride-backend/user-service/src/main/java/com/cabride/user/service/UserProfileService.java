package com.cabride.user.service;


import com.cabride.user.dto.request.UpdateProfileRequest;
import com.cabride.user.dto.response.UserProfileResponse;

import java.util.UUID;

public interface UserProfileService {
    UserProfileResponse getMyProfile(UUID userId);

    UserProfileResponse updateMyProfile(UUID userId, UpdateProfileRequest request);

}
