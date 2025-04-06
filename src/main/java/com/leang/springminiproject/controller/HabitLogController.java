package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.request.HabitLogRequest;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.HabitLogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/habit-log")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin()
public class HabitLogController {

    private final HabitLogService habitLogService;

    @GetMapping
    public ResponseEntity<?> getAllLogsByHabitId(@RequestParam UUID habitId, @RequestParam(defaultValue = "1") @Positive Integer page, @RequestParam(defaultValue = "10") @Positive Integer size) {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .success(true)
                        .message("Fetched all habit logs for the specified habit ID successfully!")
                        .status(HttpStatus.OK)
                        .payload(habitLogService.habitLogService(page, size, habitId))
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<HabitLog>> createHabitLog(@RequestBody HabitLogRequest habitLogRequest) {
        HabitLog habitLog = habitLogService.createHabitLog(habitLogRequest);
        ApiResponse<HabitLog> response = ApiResponse.<HabitLog>builder()
                .success(true)
                .message("Habit log created successfully")
                .status(HttpStatus.CREATED)
                .payload(habitLog)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
