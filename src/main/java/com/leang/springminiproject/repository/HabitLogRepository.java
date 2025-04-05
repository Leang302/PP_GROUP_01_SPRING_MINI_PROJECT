package com.leang.springminiproject.repository;

import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.request.HabitLogRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface HabitLogRepository {
    HabitLog getHabitLog(UUID habitId, HabitLogRequest habitLogRequest);
}
