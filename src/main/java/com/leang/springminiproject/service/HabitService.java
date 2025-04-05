package com.leang.springminiproject.service;

import com.leang.springminiproject.model.entity.Habit;

import java.util.List;

public interface HabitService {
    List<Habit> getAllHabit(Integer page, Integer size);
}
