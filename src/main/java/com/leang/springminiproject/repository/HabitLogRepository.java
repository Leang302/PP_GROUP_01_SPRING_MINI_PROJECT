package com.leang.springminiproject.repository;

import com.leang.springminiproject.model.entity.HabitLog;
import com.leang.springminiproject.model.request.HabitLogRequest;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habit", column = "{habitId=habit_id, userId=app_user_id}", one = @One(select = "com.leang.springminiproject.repository.HabitRepository.getHabitById"))
    })

    @Select("""
                    select * from habit_logs hl
                 inner join habits h on hl.habit_id=h.habit_id
                             where hl.habit_id=#{habitId} offset (#{page}-1)*#{size} limit #{size}; 
            """)
    List<HabitLog> getLogsByHabitId(Integer page, Integer size, UUID habitId);

    @Result(property = "habitLogId", column = "habit_log_id")
    @Select("""
                    insert into habit_logs values (default,default,#{request.status},#{xpEarned},#{request.habitId}) returning *;
            """)
    HabitLog createHabitLog(@Param("request") HabitLogRequest habitLogRequest, Integer xpEarned);

    @ResultMap("habitLogMapper")
    @Select("""
                SELECT * FROM habit_logs hl
                INNER JOIN habits h ON hl.habit_id = h.habit_id
                WHERE hl.habit_log_id = #{habitLogId}
            """)
    HabitLog getHabitLogByLogId(UUID habitLogId);
}
