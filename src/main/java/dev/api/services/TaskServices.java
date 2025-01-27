package dev.api.services;

import dev.api.entity.Task;
import dev.api.enums.Priority;
import dev.api.enums.Status;
import dev.api.repository.TaskRepository;
import dev.api.web.dto.CommentDTO;
import dev.api.web.dto.TaskDTO;
import dev.api.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServices {

    private final TaskRepository taskRepository;

    public List<TaskDTO> findAllTask() {
        return taskRepository.findAll().stream()
                .map(this::convertToTaskDTO)
                .toList();
    }

    public Task findByIdTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("вамивап"));
    }

    public Task addNewTask(TaskDTO taskDTO) {
        return taskRepository.save(Task.builder()
                .heading(taskDTO.getHeading())
                .description(taskDTO.getDescription())
                .status(Status.getStatus(taskDTO.getStatus()))
                .priority(Priority.getPriority(taskDTO.getPriority()))
                .build());
    }

    public Task updateTask(Long id, TaskDTO taskDTO) {
        Task task = findByIdTask(id);
        if (taskDTO.getHeading() != null) {
            task.setHeading(taskDTO.getHeading());
        }
        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }
        task.setStatus(Status.getStatus(taskDTO.getStatus()));
        task.setPriority(Priority.getPriority(taskDTO.getPriority()));
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDTO convertToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setHeading(task.getHeading());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus().getId());
        taskDTO.setPriority(task.getPriority().getId());

        // Маппинг пользователей
        Set<UserDTO> userDTOSet = task.getUsers().stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    return userDTO;
                })
                .collect(Collectors.toSet());

        Set<CommentDTO> commentDTOSet = task.getComments().stream()
                .map(comment -> {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setId(comment.getId());
                    commentDTO.setText(comment.getText());

                    UserDTO commentUserDTO = new UserDTO();
                    commentUserDTO.setId(comment.getUser().getId());
                    commentUserDTO.setUsername(comment.getUser().getUsername());
                    commentUserDTO.setEmail(comment.getUser().getEmail());

                    commentDTO.setUser(commentUserDTO);
                    return commentDTO;
                })
                .collect(Collectors.toSet());

        taskDTO.setUsers(userDTOSet);
        taskDTO.setComments(commentDTOSet);

        return taskDTO;
    }
}
