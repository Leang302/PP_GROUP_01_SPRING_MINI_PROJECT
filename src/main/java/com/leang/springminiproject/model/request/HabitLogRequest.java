package com.leang.springminiproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
    private String status = "COMPLETED";
    private UUID habitId;
}
