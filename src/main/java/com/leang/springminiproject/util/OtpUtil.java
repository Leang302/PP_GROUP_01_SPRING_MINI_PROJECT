package com.leang.springminiproject.util;

import java.util.Random;

public class OtpUtil {
    public static String generateOtp(int length) {
        return new Random()
                .ints(length, 0, 10)
                .mapToObj(String::valueOf)
                .reduce("", String::concat);
    }
}
