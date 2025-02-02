package dev.task_manager.web.dto.user;

import dev.task_manager.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDTO {

    @NotNull(message = "id не должен быть 0", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name не должен быть 0", groups = {OnUpdate.class, OnUpdate.class})
    @Length(max = 255, message = "Name не должно быть меньше 255 символов", groups = OnUpdate.class)
    private String name;

    @NotNull(message = "Username не должен быть 0", groups = {OnUpdate.class, OnUpdate.class})
    @Length(max = 255, message = "Username не должно быть меньше 255 символов", groups = OnUpdate.class)
    private String userName;

    private String password;

    private String passwordConfirmation;

}
