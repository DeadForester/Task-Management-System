package dev.task_manager.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    @NotNull(message = "Username не должен быть ноль")
    private String username;

    @NotNull(message = "Password не должен быть ноль")
    private String password;

}
