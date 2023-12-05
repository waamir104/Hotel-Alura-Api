package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.User.UserRegisterRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.User.UserResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.User.UserUpdateRequest;
import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.DuplicateRecordException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
    
    @Autowired
    private IUserRepository<User> userRepository;
    @Autowired
    private IRoleRepository<Role> roleRepository;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register (
        @RequestBody @Valid UserRegisterRequest request
    ) {
        if (userRepository.getUsernameCount(request.username()) != 0) throw new DuplicateRecordException("User already exists.");
        Role role = roleRepository.getById(request.roleId()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Role not found.");
            }
        );
        User user = User.builder()
            .username(request.username())
            .password(request.password())
            .createdAt(LocalDateTime.now())
            .enabled(true)
            .role(role)
            .build();
        user = userRepository.create(user);
        return ResponseEntity
            .status(200)
            .body(
                MessageResponse.builder()
                    .message(String.format("User registered with id: %d", user.getId()))
                    .build()
            );
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> list (
        @PageableDefault(size = 10) Pageable pagination
    ) {
        return ResponseEntity
            .status(200)
            .body(
                userRepository.list().stream().map(UserResponse::new).toList()
            ); 
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> fetchById (
        @PathVariable @NotNull Long id
    ) {
        User user = userRepository.getById(id).orElseThrow(
            () -> {
                throw new ApiNotFoundException("User not found.");
            }
        );
        return ResponseEntity
            .status(200)
            .body(
                new UserResponse(user)
            );
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> fetchById (
        @PathVariable @NotEmpty String username
    ) {
        User user = userRepository.getByUsername(username).orElseThrow(
            () -> {
                throw new ApiNotFoundException("User not found.");
            }
        );
        return ResponseEntity
            .status(200)
            .body(
                new UserResponse(user)
            );
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update (
        @RequestBody @Valid UserUpdateRequest request
    ) {
        User user = userRepository.getById(request.id()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("User not found.");
            }
        );
        Role role = roleRepository.getById(request.roleId()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Role not found.");
            }
        );
        user.setUsername(request.username());
        user.setRole(role);
        userRepository.update(user);
        return ResponseEntity
            .status(200)
            .body(
                MessageResponse.builder()
                    .message("User updated successfully")
                    .build()
            );
    }
}
