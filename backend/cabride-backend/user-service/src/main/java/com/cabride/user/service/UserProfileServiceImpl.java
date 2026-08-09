package com.cabride.user.service;

import com.cabride.user.dto.request.UpdateProfileRequest;
import com.cabride.user.dto.response.UserProfileResponse;
import com.cabride.user.entity.UserProfile;
import com.cabride.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileResponse getMyProfile(UUID userId) {
        UserProfile profile=userProfileRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("User Profile not found"));

        return maptoResponse(profile);
    }

    @Override
    public UserProfileResponse updateMyProfile(UUID userId, UpdateProfileRequest request) {
        UserProfile profile=userProfileRepository.findByUserId(userId)
                .orElseThrow(()->new RuntimeException("User Profile not found"));

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setProfileImageUrl(request.getProfileImageUrl());

        UserProfile updated=userProfileRepository.save(profile);

        return maptoResponse(updated);

    }

    private UserProfileResponse maptoResponse(UserProfile profile){
        return UserProfileResponse.builder()
                .userId(profile.getUserId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .phoneNumber(profile.getPhoneNumber())
                .profileImageUrl(profile.getProfileImageUrl())
                .build();
    }
}
