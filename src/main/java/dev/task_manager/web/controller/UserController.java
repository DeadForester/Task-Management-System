package dev.task_manager.web.controller;

import dev.task_manager.model.task.Task;
import dev.task_manager.model.user.User;
import dev.task_manager.services.TaskService;
import dev.task_manager.services.UserService;
import dev.task_manager.web.dto.task.TaskDTO;
import dev.task_manager.web.dto.user.UserDTO;
import dev.task_manager.web.dto.validation.OnCreate;
import dev.task_manager.web.mappers.TaskMapper;
import dev.task_manager.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    public UserDTO update(@Validated(OnCreate.class) @RequestBody UserDTO dto){
        User user = userMapper.toEntity(dto);
        User updateUser = userService.update(user);
        return userMapper.toDto(updateUser);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id){
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getTasksByUserId(@PathVariable Long id){
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDTOList(tasks);
    }

    @PostMapping("/{id}/tasks")
    public TaskDTO createTask(@PathVariable Long id,@Validated(OnCreate.class) @RequestBody TaskDTO dto){
        Task task = taskMapper.toEntity(dto);
        Task createTask = taskService.create(task, id);
        return taskMapper.toDTO(createTask);
    }







}
