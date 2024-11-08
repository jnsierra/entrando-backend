package co.com.entrando.pub.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    boolean sendSimpleMessage(String to, String subject, String text);
    boolean sendHtmlMessage(String to, String subject, String htmlBody)throws MessagingException;
}
