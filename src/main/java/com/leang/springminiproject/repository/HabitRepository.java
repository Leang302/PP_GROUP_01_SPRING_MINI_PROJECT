package com.leang.springminiproject.repository;

import com.leang.springminiproject.config.UUIDTypeHandler;
import com.leang.springminiproject.model.entity.Habit;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
    @Results(id = "habitMapper",
    value = {
            @Result(property = "habitId", column = "habit_id", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = UUIDTypeHandler.class),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "appUserResponse", column = "app_user_id", one = @One(select = "com.leang.springminiproject.repository.AppUserRepository.getUserById")),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
        SELECT * FROM habits
        OFFSET #{offset} LIMIT #{size}
    """)
    List<Habit> getAllHabit(int offset, Integer size);
}
