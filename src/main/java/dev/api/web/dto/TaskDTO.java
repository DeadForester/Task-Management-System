package dev.api.web.dto;

import dev.api.enums.Priority;
import dev.api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private String heading;
    private String description;
    private Status status;
    private Priority priority;
    private Set<UserDTO> users;
    private Set<CommentDTO> comments;
}