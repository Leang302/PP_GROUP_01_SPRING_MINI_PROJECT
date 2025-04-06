package com.leang.springminiproject.model.entity;

import com.leang.springminiproject.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLog {
    private UUID habitLogId ;
    private LocalDate logDate;
    private Status status ;
    private Integer xpEarned;
    private Habit habit;
}
