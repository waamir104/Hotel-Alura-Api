package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.User.UserRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.User.UserResponse;
import dev.waamir.hotelaluraapi.application.service.Resource.UserResourceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserResourceService userResourceService;
    
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponse> register(
        @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userResourceService.register(userRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserResponse> authenticate(
        @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity.ok(userResourceService.authenticate(userRequest));
    }

    @PutMapping("/verify/{type}/{encodedUserId}")
    @Transactional
    public ResponseEntity<MessageResponse> verify(
        @PathVariable String type,
        @PathVariable String encodedUserId
    ) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/user/verify/" + type + "/" + encodedUserId).toUriString();
        return ResponseEntity.ok(userResourceService.verify(type, encodedUserId, url));
    }
}
