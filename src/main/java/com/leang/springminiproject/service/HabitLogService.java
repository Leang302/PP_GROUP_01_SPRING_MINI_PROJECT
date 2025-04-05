package com.leang.springminiproject.service;

import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.request.HabitLogRequest;

import java.util.UUID;

public interface HabitLogService {
    HabitLog getHabitLog(UUID habitId, HabitLogRequest habitLogRequest);
}
