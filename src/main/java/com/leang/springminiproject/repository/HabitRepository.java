package com.leang.springminiproject.repository;

import com.leang.springminiproject.model.entity.Habit;
import com.leang.springminiproject.model.request.HabitRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
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
    @Select("""
                SELECT * FROM habits
                    WHERE app_user_id = #{userId}
                OFFSET #{offset} LIMIT #{size}
            """)
    List<Habit> getAllHabit(Integer offset, Integer size, UUID userId);


    @ResultMap("habitMapper")
    @Select("""
        INSERT INTO habits VALUES (default,#{req.title},#{req.description},#{req.frequency},default,#{userId},default)
        RETURNING *;
    """)
    Habit saveHabit(@Param("req") HabitRequest habitRequest, UUID userId);

    @ResultMap("habitMapper")
    @Select("""
               SELECT * FROM habits WHERE habit_id =#{habitId} AND app_user_id = #{userId}
            """)
    Habit getHabitById(UUID habitId, UUID userId);

    @ResultMap("habitMapper")
    @Select("""
        UPDATE habits
        SET title = #{req.title},
            description = #{req.description},
            frequency = #{req.frequency}
        WHERE habit_id = #{habitId} AND app_user_id = #{userId}
        RETURNING *;
    """)
    Habit updateHabitById(UUID habitId, @Param("req") HabitRequest habitRequest, UUID userId);

    @ResultMap("habitMapper")
    @Select("""
        DELETE FROM habits
        WHERE habit_id = #{habitId} AND app_user_id = #{userId}
    """)
    void deleteHabitById(UUID habitId, UUID userId);
}
