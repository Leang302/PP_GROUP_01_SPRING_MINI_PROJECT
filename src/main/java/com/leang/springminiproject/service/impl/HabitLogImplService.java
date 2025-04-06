package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.enumration.Status;
import com.leang.springminiproject.model.request.HabitLogRequest;
import com.leang.springminiproject.repository.AppUserRepository;
import com.leang.springminiproject.repository.HabitLogRepository;
import com.leang.springminiproject.service.HabitLogService;
import com.leang.springminiproject.service.HabitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HabitLogImplService implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitService habitService;
    private final AppUserRepository appUserRepository;

    @Override
    public HabitLog createHabitLog(HabitLogRequest habitLogRequest) {
        habitService.getHabitById(habitLogRequest.getHabitId());

        Integer xpEarned = habitLogRequest.getStatus() == Status.COMPLETED ? 10 : 0;
        HabitLog habitLog = habitLogRepository.createHabitLog(habitLogRequest, xpEarned);

        HabitLog habitLogByLogId = habitLogRepository.getHabitLogByLogId(habitLog.getHabitLogId());

        int totalXp = habitLogByLogId.getHabit().getAppUserResponse().getXp() + xpEarned;
        UUID userId = habitLogByLogId.getHabit().getAppUserResponse().getAppUserId();
        Integer level = habitLogByLogId.getHabit().getAppUserResponse().getLevel();

        Profile updatedProfile = appUserRepository.updateUserLevelAndXpById(userId, level, totalXp);

        if (totalXp >= 200) {
            level = (int) (double) (totalXp / 100);
            System.out.println("level:" + level);
            updatedProfile = appUserRepository.updateUserLevelAndXpById(userId, level, totalXp);
        }
        habitLogByLogId.getHabit().setAppUserResponse(updatedProfile);
        return habitLogByLogId;
    }

    @Override
    public List<HabitLog> habitLogService(Integer page, Integer size, UUID habitId) {
        habitService.getHabitById(habitId);
        return habitLogRepository.getLogsByHabitId(page, size, habitId);
    }
}

