package com.leang.springminiproject.service.impl;


import com.leang.springminiproject.exception.InvalidException;
import com.leang.springminiproject.exception.NotVerifiedException;
import com.leang.springminiproject.model.entity.AppUser;
import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.AppUserRequest;
import com.leang.springminiproject.repository.AppUserRepository;
import com.leang.springminiproject.service.AppUserService;
import com.leang.springminiproject.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        AppUser userByIdentifier = appUserRepository.getUserByIdentifier(identifier);
        if (userByIdentifier == null) {
            throw new InvalidException("Invalid username, email, or password. Please check your credentials and try again.");
        }
        if (!userByIdentifier.getIsVerified()) {
            throw new NotVerifiedException("Your email address is not verified yet. Please verify your email before logging in.");
        }
        return userByIdentifier;
    }

    public UserDetails loadUserByUsername(String identifier, String password) throws UsernameNotFoundException {
        AppUser userByIdentifier = appUserRepository.getUserByIdentifier(identifier);
        if (userByIdentifier == null || !passwordEncoder.matches(password, userByIdentifier.getPassword())) {
            throw new InvalidException("Invalid username, email, or password. Please check your credentials and try again.");
        }


        if (!userByIdentifier.getIsVerified()) {
            throw new NotVerifiedException("Your email address is not verified yet. Please verify your email before logging in.");
        }
        return userByIdentifier;
    }

    @SneakyThrows
    @Override
    public Profile register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Profile registeredUser = appUserRepository.register(request);
        otpService.sendOtp(registeredUser.getEmail());
        return registeredUser;
    }

}
