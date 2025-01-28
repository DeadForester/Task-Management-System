package dev.api.converter;

import dev.api.entity.Task;
import dev.api.web.dto.TaskDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    private final ModelMapper modelMapper;
    private final UserConverter userConverter;
    private final CommentConverter commentConverter;

    @PostConstruct
    public void init() {
        modelMapper.addConverter(new PriorityConverter());
        modelMapper.addConverter(new StatusConverter());
    }

    public TaskDTO convertTaskToTaskDTO(Task task) {
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        taskDTO.setUsers(task.getUsers().stream()
                .map(userConverter::convertUserTOUserDTO)
                .collect(Collectors.toSet()));
        taskDTO.setComments(task.getComments().stream()
                .map(commentConverter::convertCommentToCommentDTO)
                .collect(Collectors.toSet()));
        return taskDTO;
    }

    public Task convertTaskDTOToTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setUsers(taskDTO.getUsers().stream()
                .map(userConverter::convertUserDTOtoUser)
                .collect(Collectors.toSet()));
        task.setComments(taskDTO.getComments().stream()
                .map(commentConverter::convertCommentDTOToComment)
                .collect(Collectors.toSet()));
        return task;
    }
}