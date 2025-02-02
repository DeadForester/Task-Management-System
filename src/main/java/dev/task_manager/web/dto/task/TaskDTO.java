package dev.task_manager.web.dto.task;


import dev.task_manager.model.task.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private Status status;

    private LocalDateTime localDateTime;
}
