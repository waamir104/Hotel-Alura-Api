package dev.waamir.hotelaluraapi.application.service.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static dev.waamir.hotelaluraapi.application.enumeration.RoleType.*;

import java.time.LocalDateTime;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.User.UserRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.User.UserResponse;
import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.UserNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.security.JWT.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserResourceService {

    private final IUserRepository<User> userRepository;
    private final IRoleRepository<Role> roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public UserResponse register(UserRequest userRequest) {
        Role role = roleRepository.getByName(GUEST.get())
            .orElseThrow(() -> new ApiException("An error ocurred processing the creation of the user."));
        User user = User.builder()
            .username(userRequest.getUsername())
            .password(userRequest.getPassword())
            .createdAt(LocalDateTime.now())
            .role(role)
            .build();
        user = userRepository.create(user);
        String jwt = jwtService.generateJwt(user);
        return UserResponse.builder()
            .token(jwt)
            .build();
    }

    public UserResponse authenticate(UserRequest userRequest) {
        if (userRepository.getUsernameCount(userRequest.getUsername()) != 1) throw new UserNotFoundException("");
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(),
                userRequest.getPassword()
            )
        );
        User user = userRepository.getByUsername(userRequest.getUsername())
            .orElseThrow(() -> new ApiException("An error occurred processing the authentication of the user."));
        String jwt = jwtService.generateJwt(user);
        return UserResponse.builder()
            .token(jwt)
            .build();
    }
    
    public MessageResponse verify(String type, String userIdEncoded, String url) {
        userRepository.enable(type, userIdEncoded, url);
        return MessageResponse.builder()
            .message("Account verified")
            .build();
    }
}
