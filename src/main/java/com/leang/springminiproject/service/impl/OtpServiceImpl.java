package com.leang.springminiproject.service.impl;

import com.leang.springminiproject.exception.InvalidException;
import com.leang.springminiproject.exception.NotFoundException;
import com.leang.springminiproject.model.entity.AppUser;
import com.leang.springminiproject.repository.AppUserRepository;
import com.leang.springminiproject.service.OtpManager;
import com.leang.springminiproject.service.OtpService;
import com.leang.springminiproject.util.OtpUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final JavaMailSender mailSender;
    private final OtpManager otpManager;
    private final SpringTemplateEngine templateEngine;
    private final AppUserRepository appUserRepository;


    @SneakyThrows
    @Override
    public void sendOtp(String targetEmail) {
        AppUser userByIdentifier = appUserRepository.getUserByIdentifier(targetEmail);
        if (userByIdentifier == null) {
            throw new NotFoundException("User with gmail: " + targetEmail + " not found");
        }
        String otp = OtpUtil.generateOtp(6);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        Context context = new Context();
        context.setVariable("otp", otp);
        String htmlContent = templateEngine.process("opt-email.html", context);
        helper.setTo(targetEmail);
        helper.setSubject("Your OTP Code");
        helper.setText(htmlContent, true);
        helper.setFrom("your-email@gmail.com");
        mailSender.send(mimeMessage);
        //add otp to map
        otpManager.storeOtp(targetEmail,otp);

    }

    @Override
    public void verifyOTP(String email, String otp) {
        AppUser userByIdentifier = appUserRepository.getUserByIdentifier(email);
        if (userByIdentifier == null) {
            throw new NotFoundException("User with gmail: " + email + " not found");
        }
        boolean valid = otpManager.verifyOtp(email, otp);
        if (valid) {
            appUserRepository.verifyUser(email);
            otpManager.clearOtp(email);
            return;
        }
        throw new InvalidException("Invalid OTP code");
    }
}
