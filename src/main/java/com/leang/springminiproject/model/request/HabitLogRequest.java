package com.leang.springminiproject.model.request;

import com.leang.springminiproject.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitLogRequest {

    private Status status;
    private UUID habitId;

}
