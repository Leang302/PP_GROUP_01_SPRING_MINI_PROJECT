package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.HabitService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.time.Instant;

import java.util.List;


@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin()
public class HabitController {

    private final HabitService  habitService;


    @GetMapping()
    public ResponseEntity<ApiResponse<List<Habit>>> getAllHabit (@RequestParam (defaultValue = "1") Integer page, @RequestParam (defaultValue = "10") Integer size){

        List<Habit> habits = habitService.getAllHabit(page,size);
       ApiResponse<List<Habit>> apiResponse = ApiResponse.<List<Habit>>builder()
               .success(true)
               .message("Fetched all habits successfully!")
               .status(HttpStatus.OK)
               .payload(habits)
               .timestamps(Instant.now())
               .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
