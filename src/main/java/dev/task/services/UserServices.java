package dev.task.services;

import dev.task.entity.User;
import dev.task.repository.UserRepository;
import dev.task.web.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User create(UserDTO userDTO) {
        return userRepository.save(User.builder()
                .name(userDTO.getName())
                .build());

    }


}
