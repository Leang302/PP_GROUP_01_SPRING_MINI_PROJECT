package com.leang.springminiproject.service;


import com.leang.springminiproject.model.request.AppUserRequest;
import com.leang.springminiproject.model.response.AppUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AppUserService extends UserDetailsService {
    AppUserResponse register(AppUserRequest request);
}
