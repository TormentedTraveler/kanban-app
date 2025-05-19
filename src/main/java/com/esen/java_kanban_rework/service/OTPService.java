package com.esen.java_kanban_rework.service;

import com.esen.java_kanban_rework.dto.EmailDetails;
import com.esen.java_kanban_rework.dto.OTPRequest;
import com.esen.java_kanban_rework.dto.VerifyDTO;
import com.esen.java_kanban_rework.entity.OTP;
import com.esen.java_kanban_rework.exception_handlers.IncorrectPasswordException;
import com.esen.java_kanban_rework.repository.OTPRepository;
import com.esen.java_kanban_rework.repository.UserRepository;
import com.esen.java_kanban_rework.utils.AppUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OTPService {
    private final OTPRepository otpRepository;
    private final EmailService emailService;

    public OTPService(OTPRepository otpRepository, EmailService emailService) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    public void sendOTP(OTPRequest otpRequest) {
        OTP existingOTP = otpRepository.findByEmail(otpRequest.getEmail());
        if (existingOTP != null) {
            otpRepository.delete(existingOTP);
        }

        String otp = AppUtils.generate6Digits();
        otpRepository.save(OTP.builder()
                        .email(otpRequest.getEmail())
                        .otp(otp)
                        .expiryDateTime(LocalDateTime.now().plusMinutes(3))
                .build());
        EmailDetails emailDetails = EmailDetails.builder()
                .to(otpRequest.getEmail())
                .subject("OTP verification")
                .message("Your code is " + otp)
                .build();
        emailService.sendEmail(emailDetails);
    }

    public boolean isOtpValid(VerifyDTO verifyDTO) {
        OTP otp = otpRepository.findByEmail(verifyDTO.getEmail());
        if (otp == null) {
            throw new IncorrectPasswordException("Incorrect otp");
        }
        if (otp.getExpiryDateTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectPasswordException("Expired otp");
        }
        if (!otp.getOtp().equals(verifyDTO.getOtp())) {
            throw new RuntimeException("Invalid otp");
        }
        return true;
    }
}
