package dev.api.services;

import dev.api.entity.Task;
import dev.api.enums.Priority;
import dev.api.enums.Status;
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

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

}
