package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.request.HabitLogRequest;
import com.leang.springminiproject.repository.HabitLogRepository;
import com.leang.springminiproject.service.HabitLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class HabitLogImplService implements HabitLogService {

    private final HabitLogRepository habitLogRepository;

    @Override
    public HabitLog getHabitLog(UUID habitId, HabitLogRequest habitLogRequest) {

        return habitLogRepository.getHabitLog(habitId, habitLogRequest);
    }
}
