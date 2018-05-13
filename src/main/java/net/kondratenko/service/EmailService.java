package net.kondratenko.service;

public interface EmailService {

    void sendEmail(String toAddress, String subject, String msgBody);

}