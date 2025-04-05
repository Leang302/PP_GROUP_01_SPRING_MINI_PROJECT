package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.HabitLogRequest;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.HabitLogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @PostMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<HabitLog>> getHabitLog(@PathVariable("habit-id") UUID habitId, @RequestBody HabitLogRequest habitLogRequest) {
        HabitLog habitLog = habitLogService.getHabitLog(habitId, habitLogRequest);
        ApiResponse<HabitLog> response = ApiResponse.<HabitLog>builder()
                .success(true)
                .message("Habit log created successfully")
                .status(HttpStatus.CREATED)
                .payload(habitLog)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
