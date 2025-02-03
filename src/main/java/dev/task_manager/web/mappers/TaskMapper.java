package dev.task_manager.web.mappers;

import dev.task_manager.model.task.Task;
import dev.task_manager.web.dto.task.TaskDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toDTO(Task task);

    List<TaskDTO> toDTOList(List<Task> tasks);

    Task toEntity(TaskDTO dto);

}
