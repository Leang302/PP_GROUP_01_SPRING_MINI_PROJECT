package com.leang.springminiproject.service;

import com.leang.springminiproject.model.entity.Habit;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabit(Integer page, Integer size);
    Habit getHabitById(UUID habitId);

}
