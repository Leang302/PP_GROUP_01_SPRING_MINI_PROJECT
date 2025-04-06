package com.leang.springminiproject.util;

import com.leang.springminiproject.model.entity.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class AuthenticationUtil {
    public static UUID getCurrentUserId() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return appUser.getAppUserId();
    }
}
