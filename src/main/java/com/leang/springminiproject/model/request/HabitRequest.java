package com.leang.springminiproject.model.request;

import com.leang.springminiproject.model.enums.HabitFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private HabitFrequency frequency;
}
