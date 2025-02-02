package dev.task_manager.web.mappers;

import dev.task_manager.model.user.User;
import dev.task_manager.web.dto.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);


}
