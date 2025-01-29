package dev.api.converter;

import dev.api.entity.Comment;
import dev.api.entity.Task;
import dev.api.entity.User;
import dev.api.web.dto.TaskDTO;
import dev.api.web.dto.UserDTO;
import dev.api.web.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    private final UserConverter userConverter;
    private final CommentConverter commentConverter;

    public TaskDTO convertTaskToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setHeading(task.getHeading());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setPriority(task.getPriority());

        Set<UserDTO> userDTOs = task.getUsers().stream()
                .map(userConverter::convertUserToUserDTO)
                .collect(Collectors.toSet());
        taskDTO.setUsers(userDTOs);

        Set<CommentDTO> commentDTOs = task.getComments().stream()
                .map(commentConverter::convertCommentToCommentDTO)
                .collect(Collectors.toSet());
        taskDTO.setComments(commentDTOs);

        return taskDTO;
    }

    public Task convertTaskDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setHeading(taskDTO.getHeading());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());

        Set<User> users = taskDTO.getUsers().stream()
                .map(userConverter::convertUserDTOToUser)
                .collect(Collectors.toSet());
        task.setUsers(users);

        Set<Comment> comments = taskDTO.getComments().stream()
                .map(commentConverter::convertCommentDTOToComment)
                .collect(Collectors.toSet());
        task.setComments(comments);

        return task;
    }
}