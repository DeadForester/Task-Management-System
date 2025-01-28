package dev.api.converter;

import dev.api.entity.User;
import dev.api.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;

    public UserDTO convertUserTOUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertUserDTOtoUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
