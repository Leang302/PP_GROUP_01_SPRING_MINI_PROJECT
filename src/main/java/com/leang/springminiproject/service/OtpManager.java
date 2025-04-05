package com.leang.springminiproject.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpManager {
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();
    private final Map<String, Long> otpExpiry = new ConcurrentHashMap<>();

    public void storeOtp(String email, String otp) {
        otpStore.put(email, otp);
        otpExpiry.put(email, System.currentTimeMillis() + 5 * 60 * 100);
    }

    public boolean verifyOtp(String email, String optRequest) {
        String storedOtp = otpStore.get(email);
        Long expiryTime = otpExpiry.get(email);
        if (System.currentTimeMillis() > expiryTime) return false;
        return storedOtp.equals(optRequest);
    }

    public void clearOtp(String email) {
        otpStore.remove(email);
        otpExpiry.remove(email);
    }
}
