package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.model.entity.Achievement;
import com.leang.springminiproject.repository.AchievementRepository;
import com.leang.springminiproject.repository.AppUserRepository;
import com.leang.springminiproject.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAllAchievements(Integer page, Integer size) {
        return achievementRepository.getAllAchievements(page, size);
    }

    @Override
    public List<Achievement> getAchievementsByUserId(Integer page, Integer size, UUID userId) {
        return achievementRepository.getAchievementsByUserId(page,size,userId);
    }
}
