package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.AuthenticationResource.AuthenticationResponse;
import dev.waamir.hotelaluraapi.adapter.dto.User.UserRequest;
import dev.waamir.hotelaluraapi.application.service.AuthenticationService.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationService authService;
    
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
