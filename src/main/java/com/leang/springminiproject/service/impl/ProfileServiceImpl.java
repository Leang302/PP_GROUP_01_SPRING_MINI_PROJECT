package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.model.entity.AppUser;
import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.ProfileRequest;
import com.leang.springminiproject.repository.AppUserRepository;
import com.leang.springminiproject.service.ProfileService;
import com.leang.springminiproject.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final AppUserRepository appUserRepository;

    @Override
    public Profile getAppUserProfile() {
        UUID userId = AuthenticationUtil.getCurrentUserId();
        return appUserRepository.getUserById(userId);
    }

    @Override
    public Profile updateAppUserProfile(ProfileRequest profileRequest) {
        UUID userId = AuthenticationUtil.getCurrentUserId();
        return appUserRepository.updateAppUser(userId, profileRequest);
    }

    @Override
    public void deleteAppUserProfile() {
        UUID userId = AuthenticationUtil.getCurrentUserId();
        appUserRepository.deleteAppUser(userId);
    }


}
