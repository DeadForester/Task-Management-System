package dev.task.services;

import dev.task.model.User;
import dev.task.repository.UserRepository;
import dev.task.web.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }


    public Optional<User> findByIdUser(Long id){
        return userRepository.findById(id);
    }


    public User createUser(UserDTO userDTO) {
        return userRepository.save(User.builder()
                .name(userDTO.getName())
                .build());

    }

    public User updateUser(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }




}
