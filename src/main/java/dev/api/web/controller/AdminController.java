package dev.api.web.controller;

import dev.api.services.TaskService;
import dev.api.web.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tasks")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAllTask() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> findByIdTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createNewTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createNewTask(taskDTO));
    }
}
