package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.request.HabitRequest;
import com.leang.springminiproject.repository.HabitRepository;
import com.leang.springminiproject.service.HabitService;
import lombok.RequiredArgsConstructor;
import com.leang.springminiproject.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final ProfileServiceImpl profileService;

    @Override
    public List<Habit> getAllHabit(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return habitRepository.getAllHabit(offset,size,profileService.getCurrentUserId());
    }

    @Override
    public Habit saveHabit(HabitRequest habitRequest) {
        return habitRepository.saveHabit(habitRequest, profileService.getCurrentUserId());
    }


    @Override
    public Habit getHabitById(UUID habitId) {
        Habit  habit = habitRepository.getHabitById(habitId,profileService.getCurrentUserId());
        if(habit == null){
            throw new NotFoundException("Habit ID " + habitId +" is not found");
        }
        return habit;
    }

    @Override
    public Habit updateHabitById(UUID habitId, HabitRequest habitRequest) {
        Habit habit = habitRepository.updateHabitById(habitId,habitRequest,profileService.getCurrentUserId());
        if(habit == null){
            throw new NotFoundException("Habit ID " + habitId +" is not found");
        }
        return habit;
    }

    @Override
    public Habit deleteHabitById(UUID habitId) {
        if(habitRepository.getHabitById(habitId,profileService.getCurrentUserId()) == null){
            throw new NotFoundException("Habit ID " + habitId +" is not found");
        }
        return habitRepository.deleteHabitById(habitId,profileService.getCurrentUserId());
    }
}
