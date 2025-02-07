package dev.task_manager.repository;

import dev.task_manager.model.user.Role;
import dev.task_manager.model.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUserName(String userName);

    void update(User user);

    void create(User user);

    void insertUserRole(Long userId, Role role);

    boolean isTaskOrder(Long userId, Long taskId);

    void delete(Long id);
}
