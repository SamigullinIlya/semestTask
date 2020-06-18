package ru.itis.univer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.univer.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Integer subjectsCount;

    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getUsername())
                .id(user.getId())
                .subjectsCount(user.getSubjects().size())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}

