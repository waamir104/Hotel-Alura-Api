package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.AuthenticationResource.AuthenticationResponse;
import dev.waamir.hotelaluraapi.adapter.dto.User.UserRequest;
import dev.waamir.hotelaluraapi.application.service.Resource.UserResourceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserResourceService authService;
    
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(authService.register(userRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(authService.authenticate(userRequest));
    }
}