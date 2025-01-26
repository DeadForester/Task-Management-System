package dev.api.services;

import dev.api.entity.Task;
import dev.api.repository.TaskRepository;
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

    public Task addNewTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updateTask) {
        Task task = findByIdTask(id);
        if (updateTask.getHeading() != null) {
            task.setHeading(updateTask.getHeading());
        }
        if (updateTask.getDescription() != null) {
            task.setDescription(updateTask.getDescription());
        }
        if (updateTask.getStatus() != null) {
            task.setStatus(updateTask.getStatus());
        }
        if (updateTask.getPriority() != null) {
            task.setPriority(updateTask.getPriority());
        }
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }


}
