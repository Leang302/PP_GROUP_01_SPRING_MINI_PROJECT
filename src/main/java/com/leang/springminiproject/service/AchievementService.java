package com.leang.springminiproject.service;

import com.leang.springminiproject.model.entity.Achievement;

import java.util.List;
import java.util.UUID;

public interface AchievementService {
    List<Achievement> getAllAchievements(Integer page, Integer size);
    List<Achievement> getAchievementsByUserId(Integer page, Integer size, UUID userId);
}
