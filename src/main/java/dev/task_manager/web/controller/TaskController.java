package dev.task_manager.web.controller;

import dev.task_manager.model.task.Task;
import dev.task_manager.services.TaskService;
import dev.task_manager.web.dto.task.TaskDTO;
import dev.task_manager.web.dto.validation.OnUpdate;
import dev.task_manager.web.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PutMapping
    public TaskDTO update(@Validated(OnUpdate.class) @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @GetMapping("/{id}")
    public TaskDTO getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

}
