package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.request.HabitRequest;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin()
public class HabitController {

    private final HabitService habitService;

    @Operation(summary = "Get all habits")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabit(@RequestParam(defaultValue = "1") @Positive Integer page, @RequestParam(defaultValue = "10") @Positive Integer size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<List<Habit>>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Fetched all habits successfully!")
                .payload(habitService.getAllHabit(page, size))
                .build());
    }

    @Operation(summary = "Create a new habit")
    @PostMapping
    public ResponseEntity<ApiResponse<Habit>> saveHabit(@RequestBody @Valid HabitRequest habitRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Habit created successfully!")
                .payload(habitService.saveHabit(habitRequest))
                .build());
    }

    @Operation(summary = "Get habit by ID")
    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabitById(@PathVariable("habit-id") UUID habitId) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Habit fetched successfully!")
                .payload(habitService.getHabitById(habitId))
                .build());
    }

    @Operation(summary = "Update habit by ID")
    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabitById(@PathVariable("habit-id") UUID habitId, @RequestBody @Valid HabitRequest habitRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Habit updated successfully!")
                .payload(habitService.updateHabitById(habitId, habitRequest))
                .build());
    }

    @Operation(summary = "Delete habit by ID")
    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<?>> deleteHabitById(@PathVariable("habit-id") UUID habitId) {
        habitService.deleteHabitById(habitId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Habit deleted successfully!")
                .build());
    }
}
