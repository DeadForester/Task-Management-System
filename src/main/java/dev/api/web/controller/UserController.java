package dev.api.web.controller;

import dev.api.model.User;
import dev.api.services.UserServices;
import dev.api.web.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userServices.findAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
        return ResponseEntity.ok(userServices.findByIdUser(id));
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userServices.createUser(userDTO));
    }


    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(userServices.updateUser(id, user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
