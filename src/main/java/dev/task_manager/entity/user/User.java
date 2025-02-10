package dev.task_manager.entity.user;

import dev.task_manager.entity.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String name;

    private String userName;

    private String password;

    private String passwordConfirmation;

    private Set<Role> roles;

    private List<Task> tasks;
}
