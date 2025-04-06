package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.request.HabitRequest;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.HabitService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.time.Instant;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin()
public class HabitController {

    private final HabitService  habitService;
    private final RestClient.Builder builder;


    @GetMapping()
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabit (@RequestParam (defaultValue = "1") Integer page, @RequestParam (defaultValue = "10") Integer size
    ){

        List<Habit> habits = habitService.getAllHabit(page,size);
       ApiResponse<List<Habit>> apiResponse = ApiResponse.<List<Habit>>builder()
               .success(true)
               .status(HttpStatus.OK)
               .message("Fetched all habits successfully!")
               .payload(habits)
               .timestamps(Instant.now())
               .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public  ResponseEntity<ApiResponse<Habit>> saveHabit (@RequestBody @Valid HabitRequest habitRequest){
        Habit habit = habitService.saveHabit(habitRequest);

        ApiResponse<Habit> apiResponse = ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Habit created successfully!")
                .payload(habit)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> getHabitById(@PathVariable ("habit-id") UUID habitId){
        Habit habit = habitService.getHabitById(habitId);
        ApiResponse<Habit> apiResponse = ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Habit fetched successfully!")
                .payload(habit)
                .timestamps(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<Habit>> updateHabitById(@PathVariable ("habit-id") UUID habitId, @RequestBody @Valid HabitRequest habitRequest){
        Habit habit = habitService.updateHabitById(habitId,habitRequest);
        ApiResponse<Habit> apiResponse = ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Update habit  id "+ habitId +" successfully!")
                .payload(habit)
                .timestamps(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{habit-id}")
    public ResponseEntity<ApiResponse<?>> deleteHabitById(@PathVariable ("habit-id") UUID habitId){
        Habit habit = habitService.deleteHabitById(habitId);
        ApiResponse<Habit> apiResponse = ApiResponse.<Habit>builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Delete habit id "+ habitId +" successfully!")
                .payload(habit)
                .timestamps(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
