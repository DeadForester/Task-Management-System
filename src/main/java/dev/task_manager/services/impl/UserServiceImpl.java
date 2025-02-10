package dev.task_manager.services.impl;

import dev.task_manager.entity.exception.ResourceNotFoundException;
import dev.task_manager.entity.user.Role;
import dev.task_manager.entity.user.User;
import dev.task_manager.repository.UserRepository;
import dev.task_manager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(()->new RuntimeException("user not found"));
    }

    @Override
    @Transactional
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.update(user);
        return user;
    }

    @Override
    @Transactional
    public User create(User user) {
        if(userRepository.findByUserName(user.getUserName()).isPresent()){
            throw new IllegalArgumentException("User already exists");
        }
        if(!user.getPassword().equals(user.getPasswordConfirmation())){
            throw new IllegalArgumentException("Password and Password confirmation do not match");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.create(user);
        Set<Role> roles = Set.of(Role.ROLE_USER);
        userRepository.insertUserRole(user.getId(), Role.ROLE_USER);
        user.setRoles(roles);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTaskOrder(Long userId, Long taskId) {
        return userRepository.isTaskOrder(userId,taskId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
