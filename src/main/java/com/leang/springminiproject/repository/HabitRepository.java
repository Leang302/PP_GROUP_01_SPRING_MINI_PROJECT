package com.leang.springminiproject.repository;

import com.leang.springminiproject.model.entity.Habit;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

}
