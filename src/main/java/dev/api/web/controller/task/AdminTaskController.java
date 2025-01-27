package dev.api.web.controller.task;

import dev.api.entity.Task;
import dev.api.services.TaskServices;
import dev.api.web.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminTaskController {
    private final TaskServices taskServices;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> findAllTask() {
        return ResponseEntity.ok(taskServices.findAllTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findByIdTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskServices.findByIdTask(id));
    }

    @PostMapping
    public ResponseEntity<Task> addNewTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskServices.addNewTask(taskDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskServices.updateTask(id, taskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskServices.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }


}
