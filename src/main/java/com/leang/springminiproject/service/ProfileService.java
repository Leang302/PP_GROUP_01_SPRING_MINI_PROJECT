package com.leang.springminiproject.service;

import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.ProfileRequest;

public interface ProfileService {
    Profile getAppUserProfile();

    Profile updateAppUserProfile(ProfileRequest profileRequest);

    void deleteAppUserProfile();
}
