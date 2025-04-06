package com.leang.springminiproject.model.request;

import com.leang.springminiproject.model.enums.HabitFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitRequest {
    @NotBlank
    @Size(max = 255)
    private String title;
    @Size(max = 255)
    private String description;
    @NotNull
    private HabitFrequency frequency;
}
