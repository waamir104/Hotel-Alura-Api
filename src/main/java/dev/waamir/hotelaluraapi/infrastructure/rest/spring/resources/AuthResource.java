package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.beans.factory.annotation.Value;
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
import dev.waamir.hotelaluraapi.adapter.dto.resource.Auth.AuthRegisterRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Auth.AuthRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Auth.AuthResetPwdRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Auth.AuthResponse;
import dev.waamir.hotelaluraapi.application.service.Resource.AuthResourceService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthResource {

    @Value("${application.front-end.host}")
    private String frontHost;

    private final AuthResourceService userResourceService;
    
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<AuthResponse> register(
        @RequestBody AuthRegisterRequest userRequest
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userResourceService.register(userRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
        @RequestBody AuthRequest userRequest
    ) {
        return ResponseEntity.ok(userResourceService.authenticate(userRequest));
    }

    @PutMapping("/verify/{type}/{encodedUserId}")
    @Transactional
    public ResponseEntity<MessageResponse> verify(
        @PathVariable String type,
        @PathVariable String encodedUserId
    ) {
        String url = frontHost.concat("/verify/" + type + "/" + encodedUserId);
        return ResponseEntity.ok(userResourceService.verify(type, encodedUserId, url));
    }

    @GetMapping("/resetPwdRequest/{username}")
    public ResponseEntity<MessageResponse> resetPwdRequest(
        @PathVariable @NotEmpty String username
    ) {
        return ResponseEntity.ok(userResourceService.resetPwdRequest(username));
    }

    @PutMapping("/resetPwd")
    public ResponseEntity<MessageResponse> resetPwd (
        @RequestBody AuthResetPwdRequest request
    ) {
        return ResponseEntity.ok(userResourceService.resetPwd(request));
    }
}
