package com.leang.springminiproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.leang.springminiproject.model.response.AppUserReponse;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habit {
    private UUID habitId;
    private String title;
    private String description;
    private String frequency;
    private Boolean isActive;
    private AppUserReponse appUserReponse;
    private Instant createdAt;
}
