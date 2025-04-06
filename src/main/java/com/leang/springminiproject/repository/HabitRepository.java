package com.leang.springminiproject.repository;

import com.leang.springminiproject.model.entity.Habit;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
    @Results(id = "habitMapper", value = {
            @Result(property = "habitId", column = "app_user_id"),
            @Result(property = "isActive", column = "is_active"),
            @Result(
                    property = "appUserResponse",
                    column = "app_user_id",
                    one = @One(select = "com.leang.springminiproject.repository.AppUserRepository.getUserById")
            ),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
                SELECT * FROM habits
                OFFSET #{offset} LIMIT #{size}
            """)
    List<Habit> getAllHabit(Integer offset, Integer size);

    @ResultMap("habitMapper")
    @Select("""
                SELECT * FROM habits where habit_id=#{habitId} and app_user_id=#{userId};
            """)
    Habit getHabitById(UUID habitId, UUID userId);
}
