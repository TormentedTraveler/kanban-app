package com.esen.java_kanban_rework.service;

import com.esen.java_kanban_rework.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailSender);
            message.setTo(emailDetails.getTo());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getMessage());

            javaMailSender.send(message);
        }catch (MailException e) {
            e.printStackTrace();
        }
    }

}
