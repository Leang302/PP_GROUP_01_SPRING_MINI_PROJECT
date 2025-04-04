package com.leang.springminiproject.service;

public interface OtpService {
    void sendOtp(String targetEmail);

    void verifyOTP(String email, String otp);
}
