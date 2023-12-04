package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
    
    @Autowired
    private IUserRepository<User> userRepository;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register (
        @RequestBody @Valid UserRegisterRequest request
    ) {
        // TODO implement the logic
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<UserResponse>> list (
        @PageableDefault(size = 10) Pageable pagination
    ) {
        // TODO implement the logic
        return null; 
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> fetchById (
        @PathVariable @NotNull Long id
    ) {
        // TODO implement the logic
        return null;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> fetchById (
        @PathVariable @NotEmpty String email
    ) {
        // TODO implement the logic
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update (
        @RequestBody @Valid UserUpdateRequest request
    ) {
        // TODO implement the logic
        return null;
    }
}
