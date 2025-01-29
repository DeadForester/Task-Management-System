package dev.api.services;

import dev.api.converter.TaskConverter;
import dev.api.entity.Comment;
import dev.api.entity.Task;
import dev.api.entity.User;
import dev.api.enums.Priority;
import dev.api.enums.Status;
import dev.api.repository.CommentRepository;
import dev.api.repository.TaskRepository;
import dev.api.web.dto.TaskDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public List<TaskDTO> findAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskConverter::convertTaskToTaskDTO)
                .toList();
    }

    public TaskDTO findTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задача с ID " + id + " не найдена!"));
        return taskConverter.convertTaskToTaskDTO(task);
    }

    public TaskDTO createNewTask(TaskDTO taskDTO) {
        Task task = taskConverter.convertTaskDTOToTask(taskDTO);
        taskRepository.save(task);
        return taskConverter.convertTaskToTaskDTO(task);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задача с ID " + id + " не найдена!"));
        existingTask.setHeading(taskDTO.getHeading());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setPriority(taskDTO.getPriority());
        taskRepository.save(existingTask);
        return taskConverter.convertTaskToTaskDTO(existingTask);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDTO updateTaskStatus(Long id, Status status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задача с ID " + id + " не найдена!"));
        task.setStatus(status);
        taskRepository.save(task);
        return taskConverter.convertTaskToTaskDTO(task);
    }

    public TaskDTO updateTaskPriority(Long id, Priority priority) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задача с ID " + id + " не найдена!"));
        task.setPriority(priority);
        taskRepository.save(task);
        return taskConverter.convertTaskToTaskDTO(task);
    }

    @Transactional
    public void updateTaskStatus(Long taskId, String userEmail, Status status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Transactional
    public void addComment(Long taskId, String userEmail, String content) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        User user = userService.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Comment comment = new Comment();
        comment.setTask(task);
        comment.setUser(user);

        commentRepository.save(comment);
    }
}