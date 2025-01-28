package dev.api.services;

import dev.api.converter.TaskConverter;
import dev.api.entity.Task;
import dev.api.repository.TaskRepository;
import dev.api.web.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskConverter taskConverter;

    public List<TaskDTO> findAllTasks() {
        return taskRepository.findAll().stream().map(taskConverter::convertTaskToTaskDTO).toList();
    }

    public TaskDTO findTaskById(Long id) {
        return taskConverter.convertTaskToTaskDTO(
                taskRepository.findById(id).
                        orElseThrow(() -> new IllegalArgumentException("Задачи с ID: " + id + " не существует!"))
        );
    }

    public TaskDTO createNewTask(TaskDTO taskDTO){
        Task task = taskConverter.convertTaskDTOToTask(taskDTO);
        taskRepository.save(task);
        return taskDTO;
    }

}
