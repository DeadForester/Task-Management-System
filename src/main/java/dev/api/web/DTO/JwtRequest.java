package dev.api.web.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

}
