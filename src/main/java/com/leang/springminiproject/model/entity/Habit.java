package com.leang.springminiproject.model.entity;

import com.leang.springminiproject.model.enums.HabitFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    private UUID habitId;
    private String title;
    private String description;
    private HabitFrequency frequency;
    private Boolean isActive;
    private Profile appUserResponse;
    private Instant createdAt;
}
