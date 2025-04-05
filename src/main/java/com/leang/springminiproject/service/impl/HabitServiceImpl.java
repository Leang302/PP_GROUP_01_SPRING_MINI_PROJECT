package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.repository.HabitRepository;
import com.leang.springminiproject.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<Habit> getAllHabit(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return habitRepository.getAllHabit(offset,size);
    }
}
