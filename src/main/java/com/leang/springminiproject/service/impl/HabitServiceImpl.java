package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.exception.NotFoundException;
import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.request.HabitRequest;
import com.leang.springminiproject.repository.HabitRepository;
import com.leang.springminiproject.service.HabitService;
import com.leang.springminiproject.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabit(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return habitRepository.getAllHabit(offset, size, AuthenticationUtil.getCurrentUserId());
    }

    @Override
    public Habit saveHabit(HabitRequest habitRequest) {
        return habitRepository.saveHabit(habitRequest, AuthenticationUtil.getCurrentUserId());
    }


    @Override
    public Habit getHabitById(UUID habitId) {
        Habit habit = habitRepository.getHabitById(habitId, AuthenticationUtil.getCurrentUserId());
        if (habit == null) {
            throw new NotFoundException("Habit ID " + habitId + " is not found");
        }
        return habit;
    }

    @Override
    public Habit updateHabitById(UUID habitId, HabitRequest habitRequest) {
        getHabitById(habitId);
        return habitRepository.updateHabitById(habitId, habitRequest, AuthenticationUtil.getCurrentUserId());
    }

    @Override
    public void deleteHabitById(UUID habitId) {
        getHabitById(habitId);
        habitRepository.deleteHabitById(habitId, AuthenticationUtil.getCurrentUserId());
    }
}
