package dev.task.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Task {

    private Long id;
    private String heading;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;


    private enum TaskStatus {
        WAITING, IN_PROGRESS, COMPLETED
    }

    private enum TaskPriority {
        HIGH, MEDIUM, LOW
    }


}
