package dev.task_manager.repository.impl;

import dev.task_manager.model.user.Role;
import dev.task_manager.model.user.User;
import dev.task_manager.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long id, Role role) {

    }

    @Override
    public boolean isTaskOrder(Long userId, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
