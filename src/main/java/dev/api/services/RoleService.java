package dev.api.services;

import dev.api.entity.Role;
import dev.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(){
        return roleRepository.findByRoleName("ROLE_USER").orElseThrow(()->new RuntimeException("Роль не найдена"));
    }
}
