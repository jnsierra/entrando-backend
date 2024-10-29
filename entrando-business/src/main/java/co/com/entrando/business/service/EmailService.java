package co.com.entrando.business.service;

public interface EmailService {
    Boolean sendHtmlMessage(String to, String subject, String htmlBody);
}
