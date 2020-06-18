package ru.itis.univer.services.impls;

import org.springframework.stereotype.Service;
import ru.itis.univer.dto.UserDto;
import ru.itis.univer.models.User;
import ru.itis.univer.repositories.UsersRepository;
import ru.itis.univer.services.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public UserDto getUser(Long userId) {
        return UserDto.from(usersRepository.getOne(userId));
    }

    @Override
    public User getOne(Long id) {
        return usersRepository.getOne(id);
    }

    @Override
    public void updateUser(User user) {
        usersRepository.save(user);
    }
}
