package com.leang.springminiproject.model.entity;

import com.leang.springminiproject.model.request.HabitLogRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLog {
    private UUID habitLogId ;
    private DateTimeFormatter logDate;
    private String status ;
    private Integer xpEarned;
    private UUID habitId;

}
