package dev.task_manager.services;

import dev.task_manager.web.dto.auth.JwtRequest;
import dev.task_manager.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);


}
