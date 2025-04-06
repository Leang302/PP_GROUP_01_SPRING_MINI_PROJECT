package com.leang.springminiproject.service;


import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.AppUserRequest;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AppUserService extends UserDetailsService {
    Profile register(AppUserRequest request);

    UserDetails loadUserByUsername(String identifier, String password);
}
