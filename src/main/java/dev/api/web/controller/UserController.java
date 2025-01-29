package dev.api.web.controller;

import dev.api.enums.Status;
import dev.api.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final TaskService taskService;

    @PutMapping("/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId,
                                              @RequestParam Status status,
                                              @AuthenticationPrincipal User user) {
        taskService.updateTaskStatus(taskId, user.getUsername(), status);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long taskId,
                                        @RequestParam String content,
                                        @AuthenticationPrincipal User user) {
        taskService.addComment(taskId, user.getUsername(), content);
        return ResponseEntity.ok().build();
    }
}
