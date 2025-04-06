package com.leang.springminiproject.service;

import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.request.HabitLogRequest;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public interface HabitLogService {
    HabitLog createHabitLog(HabitLogRequest habitLogRequest);

    List<HabitLog> habitLogService(@Positive Integer page, @Positive Integer size, UUID habitId);
}
