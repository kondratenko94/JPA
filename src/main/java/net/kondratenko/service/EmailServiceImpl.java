package net.kondratenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.property.email}")
    private String emailAddress;

    @Async
    @Override
    public void sendEmail(String toAddress, String subject, String msgBody) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailAddress);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(msgBody);
        mailSender.send(message);
    }
}