package com.leang.springminiproject.repository;

import com.leang.springminiproject.model.entity.Achievement;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AchievementRepository {
    @Results(id = "achievementMapper", value = {
            @Result(property = "achievementId", column = "achievement_id"), @Result(property = "xpRequired", column = "xp_required")
    })
    @Select("""
                select * from achievements OFFSET (#{page}-1) *#{size} limit #{size};
            """)
    List<Achievement> getAllAchievements(Integer page, Integer size);

    @ResultMap("achievementMapper")
    @Select("""
             select * from app_user_achievements auc inner join achievements a on auc.achievement_id = a.achievement_id where app_user_id=#{userId} OFFSET (#{page}-1) *#{size} limit #{size}  ;
            """)
    List<Achievement> getAchievementsByUserId(Integer page, Integer size, UUID userId);

    @ResultMap("achievementMapper")
    @Select("""
                select * from achievements where xp_required<=#{xp};
            """)
    List<Achievement> getAllAchievementsByXpRequired(Integer xp);

    @ResultMap("achievementMapper")
    @Select("""
             select * from app_user_achievements auc inner join achievements a on auc.achievement_id = a.achievement_id where app_user_id=#{userId} ;
            """)
    List<Achievement> getUserAchievements(UUID userId);

}
