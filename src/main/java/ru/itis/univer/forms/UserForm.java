package ru.itis.univer.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    @NotNull(message = "{errors.null.username}")
    @Min(value = 3, message = "{errors.incorrect.username}")
    private String username;

    @Email(message = "{errors.incorrect.email}")
    private String email;

    @Min(value = 0, message = "{errors.negative.count}")
    private Integer subjectsCount;
}
