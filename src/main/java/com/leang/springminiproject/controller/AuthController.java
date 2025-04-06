package com.leang.springminiproject.controller;

import com.leang.springminiproject.jwt.JwtService;
import com.leang.springminiproject.model.entity.Profile;
import com.leang.springminiproject.model.request.AppUserRequest;
import com.leang.springminiproject.model.request.AuthRequest;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.model.response.AuthResponse;
import com.leang.springminiproject.service.AppUserService;
import com.leang.springminiproject.service.OtpService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OtpService emailService;

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Operation(summary = "User login")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthRequest request) throws Exception {
        authenticate(request.getIdentifier(), request.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(request.getIdentifier());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok().body(
                ApiResponse.builder().
                        success(true)
                        .message("User login successfully")
                        .status(HttpStatus.OK)
                        .payload(authResponse).build()
        );
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AppUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().success(true).message("User registered successfully").status(HttpStatus.CREATED).payload(appUserService.register(request)).build());
    }

    @Operation(summary = "Verify email with OTP")
    @PostMapping("/verify")
    public ResponseEntity<?> verifyEmailWithOTP(@RequestParam String email, @RequestParam String otp) {
        emailService.verifyOTP(email, otp);
        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Email successfully verified! You can now log in.")
                .status(HttpStatus.CREATED).build());
    }

    @Operation(summary = "Resend verification OTP")
    @SneakyThrows
    @PostMapping("/resend")
    public ResponseEntity<?> resendOTP(@RequestParam String email) {
        emailService.sendOtp(email);
        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Verification OTP successfully resent to your email.").status(HttpStatus.OK).build());
    }
}
