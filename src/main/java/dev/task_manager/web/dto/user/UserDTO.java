package dev.task_manager.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.task_manager.web.dto.validation.OnCreate;
import dev.task_manager.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDTO {

    @NotNull(message = "id не должен быть null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name не должен быть null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name не должно быть меньше 255 символов", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @NotNull(message = "Username не должен быть null", groups = {OnUpdate.class, OnUpdate.class})
    @Length(max = 255, message = "Username не должно быть меньше 255 символов", groups = {OnUpdate.class, OnCreate.class})
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password не должен быть равен null", groups = {OnUpdate.class, OnCreate.class})
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "PasswordConfirmation не должен быть равен null", groups = OnCreate.class)
    private String passwordConfirmation;

}
