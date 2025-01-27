package dev.api.web.dto;

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
    private int status;
    private int priority;
    private Set<UserDTO> users;
    private Set<CommentDTO> comments;

}
