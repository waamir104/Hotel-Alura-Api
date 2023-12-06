package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import dev.waamir.hotelaluraapi.domain.model.Guest;
import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IGuestRepository;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.DuplicateRecordException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.GenericException;
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
    @Autowired
    private IGuestRepository<Guest> guestRepository;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register (
        @RequestBody @Valid UserRegisterRequest request
    ) {
        if(!request.password1().equals(request.password2())) throw new GenericException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        if (userRepository.getUsernameCount(request.username()) != 0) throw new DuplicateRecordException("User already exists.");
        Role role = roleRepository.getById(request.roleId()).orElseThrow(
            () -> {
                throw new ApiNotFoundException("Role not found.");
            }
        );
        User user = User.builder()
            .username(request.username())
            .password(request.password1())
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
    public ResponseEntity<List<UserResponse>> list () {
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
        if (role.getName().equals("GUEST")) {
            Guest guest;
            Optional<Guest> guestOp = guestRepository.getByEmail(user.getUsername());
            if (guestOp.isPresent()) {
                guest = guestOp.get();
                guest.setEmail(request.username());
                guestRepository.update(guest);
            }
        }
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
