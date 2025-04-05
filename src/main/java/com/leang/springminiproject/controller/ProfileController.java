package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.ProfileRequest;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin()
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ApiResponse<Profile>> getUserProfile() {
        return ResponseEntity.ok().body(ApiResponse.<Profile>builder().success(true).message("User profile fetched successfully!").status(HttpStatus.OK).payload(profileService.getAppUserProfile()).build());
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Profile>> updateUserProfile(@RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.ok().body(ApiResponse.<Profile>builder().success(true).message("User profile updated successfully!").status(HttpStatus.OK).payload(profileService.updateAppUserProfile(profileRequest)).build());
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Profile>> deleteUserProfile() {
        profileService.deleteAppUserProfile();
        return ResponseEntity.ok().body(ApiResponse.<Profile>builder().success(true).message("User profile deleted successfully!").status(HttpStatus.OK).build());
    }

}
