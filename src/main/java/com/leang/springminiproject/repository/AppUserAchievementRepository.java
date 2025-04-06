package com.leang.springminiproject.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface AppUserAchievementRepository {
    @Insert("""
                insert into app_user_achievements values (#{userId},#{achievementId})
            """)
    void addUserAchievement(UUID userId, UUID achievementId);
}
