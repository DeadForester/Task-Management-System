package dev.api.services;

import dev.api.entity.Task;
import dev.api.enums.Priority;
import dev.api.enums.Status;
import dev.api.repository.TaskRepository;
import dev.api.web.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServices {

    private final TaskRepository taskRepository;

    public List<Task> findAllTask() {
        return taskRepository.findAll();
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


}
