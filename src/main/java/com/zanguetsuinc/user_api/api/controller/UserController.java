package com.zanguetsuinc.user_api.api.controller;

import com.zanguetsuinc.user_api.api.assembler.UserAssembler;
import com.zanguetsuinc.user_api.api.dto.UserDto;
import com.zanguetsuinc.user_api.api.response.UserResponse;
import com.zanguetsuinc.user_api.domain.model.User;
import com.zanguetsuinc.user_api.domain.repository.UserRepository;
import com.zanguetsuinc.user_api.domain.service.UserCatalog;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserAssembler userAssembler;
    private UserRepository userRepository;
    private UserCatalog userCatalog;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userAssembler.toCollectionModel(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId){
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(userAssembler.toDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserResponse userResponse){

        User newUser = userAssembler.toResponse(userResponse);
        User userAdded = userCatalog.create(newUser);

        return userAssembler.toDto(userAdded);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserResponse userResponse,
                                              @PathVariable Long userId){
        if (!userRepository.existsById(userId)){
            return ResponseEntity.notFound().build();
        }

        User updatingUser = userAssembler.toResponse(userResponse);
       // updatingUser.setId(userId);
        User userUpdated = userCatalog.create(updatingUser);

        return ResponseEntity.ok(userAssembler.toDto(userUpdated));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        if (!userRepository.existsById(userId)){
            return ResponseEntity.notFound().build();
        }

        userCatalog.remove(userId);
        return ResponseEntity.noContent().build();
    }

}
