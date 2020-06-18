package ru.itis.univer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class UniverApplication {

    @Bean
    public DefaultHandshakeHandler handshakeHandler() {
         return new DefaultHandshakeHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(20);
    }

    public static void main(String[] args) {
        SpringApplication.run(UniverApplication.class, args);
    }

}
