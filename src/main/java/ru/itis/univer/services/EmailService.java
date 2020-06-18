package ru.itis.univer.services;

public interface EmailService {
    void sendMail(String subject, String text, String email);
}
