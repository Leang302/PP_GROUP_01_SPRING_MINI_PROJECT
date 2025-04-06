package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.Achievement;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.AchievementService;
import com.leang.springminiproject.util.AuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/achievements")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class AchievementController {
    private final AchievementService achievementService;

    @Operation(summary = "Get all achievements")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Achievement>>> getAllAchievements(@RequestParam(defaultValue = "1") @Positive Integer page, @RequestParam(defaultValue = "10") @Positive Integer size) {
        return ResponseEntity.ok().body(
                ApiResponse.<List<Achievement>>builder()
                        .success(true)
                        .message("User profile updated successfully!")
                        .status(HttpStatus.OK)
                        .payload(achievementService.getAllAchievements(page, size))
                        .build()
        );
    }

    @Operation(summary = "Get Get achievement by App User ID")
    @GetMapping("app-users")
    public ResponseEntity<ApiResponse<List<Achievement>>> getCurrentUserAchievements(@RequestParam(defaultValue = "1") @Positive Integer page, @RequestParam(defaultValue = "10") @Positive Integer size) {
        return ResponseEntity.ok().body(
                ApiResponse.<List<Achievement>>builder()
                        .success(true)
                        .message("User profile updated successfully!")
                        .status(HttpStatus.OK)
                        .payload(achievementService.getAchievementsByUserId(page, size, AuthenticationUtil.getCurrentUserId()))
                        .build()
        );
    }
}
