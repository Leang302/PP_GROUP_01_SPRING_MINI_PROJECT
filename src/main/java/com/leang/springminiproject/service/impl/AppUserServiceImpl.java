package com.leang.springminiproject.service.impl;


import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.AppUserRequest;
import com.leang.springminiproject.repository.AppUserRepository;
import com.leang.springminiproject.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.getUserByEmail(email);
    }

    @Override
    public Profile register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        //        return modelMapper.map(appUserRepository.getUserById(appUser.getAppUserId()), Profile.class);
        return appUserRepository.register(request);
    }

}
