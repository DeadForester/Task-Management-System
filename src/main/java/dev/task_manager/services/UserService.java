package dev.task_manager.services;

import dev.task_manager.model.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User update(User user);

    User create(User user);

    boolean isTaskOrder(Long userId, Long taskId);

    void delete(Long id);

}
