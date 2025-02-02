package dev.task_manager.web.dto.task;


import com.fasterxml.jackson.annotation.JsonFormat;
import dev.task_manager.model.task.Status;
import dev.task_manager.web.dto.validation.OnCreate;
import dev.task_manager.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    @NotNull(message = "id не должен быть null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title не должен быть null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Title длина должна быть больше 255 символов", groups = {OnUpdate.class, OnCreate.class})
    private String title;

    @Length(max = 4096, message = "description длина должна быть больше 4096 символов", groups = {OnUpdate.class, OnCreate.class})
    private String description;

    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localDateTime;
}
