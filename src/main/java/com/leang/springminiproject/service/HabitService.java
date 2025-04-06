package com.leang.springminiproject.service;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.request.HabitRequest;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    List<Habit> getAllHabit(Integer page, Integer size);

    Habit saveHabit(HabitRequest habitRequest);

    Habit getHabitById(UUID habitId) ;

    Habit updateHabitById(UUID habitId, HabitRequest habitRequest);

    void deleteHabitById(UUID habitId);
}
