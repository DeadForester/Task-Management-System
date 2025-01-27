package dev.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDTO {

    private String heading;
    private String description;
    private int status;
    private int priority;
}
