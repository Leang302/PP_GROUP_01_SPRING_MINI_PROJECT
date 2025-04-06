package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.exception.NotFoundException;
import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.repository.HabitRepository;
import com.leang.springminiproject.service.HabitService;
import com.leang.springminiproject.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "habit_id"),
            @Result(property = "isActive", column = "is_active"),
            @Result(
                    property = "appUserResponse",
                    column = "app_user_id",
                    one = @One(select = "com.leang.springminiproject.repository.AppUserRepository.getUserById")
            ),
            @Result(property = "createdAt", column = "created_at")
    })

    @Override
    public List<Habit> getAllHabit(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return habitRepository.getAllHabit(offset,size);
    }

    @Override
    public Habit getHabitById(UUID habitId) {
        Habit habitById = habitRepository.getHabitById(habitId, AuthenticationUtil.getCurrentUserId());
        if (habitById == null) {
            throw new NotFoundException("Habit with id " + habitId + " not found.");
        }
        return habitById;
    }
}
