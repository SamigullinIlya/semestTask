package ru.itis.univer.services.impls;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.univer.forms.SignUpForm;
import ru.itis.univer.models.User;
import ru.itis.univer.models.enums.Role;
import ru.itis.univer.models.enums.State;
import ru.itis.univer.repositories.UsersRepository;
import ru.itis.univer.services.EmailService;
import ru.itis.univer.services.SignUpService;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final ExecutorService threadPool;
    private final PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository,
                             EmailService emailService,
                             ExecutorService threadPool,
                             PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.emailService = emailService;
        this.threadPool = threadPool;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(SignUpForm form) {
        String rawPassword = form.getPassword();
        String hashPassword = passwordEncoder.encode(rawPassword);

        User user = User.builder()
                .email(form.getEmail())
                .hashPassword(hashPassword)
                .username(form.getUsername())
                .createdAt(LocalDateTime.now())
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();

        usersRepository.save(user);

        threadPool.submit(() ->
                emailService.sendMail("Регистрация",
                        user.getConfirmCode(),
                        user.getEmail())
                );
    }
}
