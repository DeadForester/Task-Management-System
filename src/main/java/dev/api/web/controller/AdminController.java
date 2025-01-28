package dev.api.web.controller;

import dev.api.services.TaskServices;
import dev.api.web.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final TaskServices taskServices;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> findAllTask() {
        return ResponseEntity.ok(taskServices.findAllTask());
    }
}
