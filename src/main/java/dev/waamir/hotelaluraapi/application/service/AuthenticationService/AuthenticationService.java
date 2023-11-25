package dev.waamir.hotelaluraapi.application.service.AuthenticationService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static dev.waamir.hotelaluraapi.application.enumeration.RoleType.*;

import java.time.LocalDateTime;

import dev.waamir.hotelaluraapi.adapter.dto.AuthenticationResource.AuthenticationResponse;
import dev.waamir.hotelaluraapi.adapter.dto.User.UserRequest;
import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;
import dev.waamir.hotelaluraapi.infrastructure.security.JWT.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUserRepository<User> userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository<Role> roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(UserRequest userRequest) {
        Role role = roleRepository.getByName(GUEST.get())
            .orElseThrow(() -> new ApiException("An error ocurred processing the creation of the user."));
        User user = User.builder()
            .username(userRequest.getUsername())
            .password(passwordEncoder.encode(userRequest.getPassword()))
            .createdAt(LocalDateTime.now())
            .role(role)
            .build();
        user = userRepository.create(user);
        String jwt = jwtService.generateJwt(user);
        return AuthenticationResponse.builder()
            .token(jwt)
            .build();
    }

    public AuthenticationResponse authenticate(UserRequest userRequest) {
        System.out.println("before");
        Authentication authentication = null;
        try {
            authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userRequest.getUsername(),
                    userRequest.getPassword()
                )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(authentication.getCredentials().toString());
        User user = userRepository.getByUsername(userRequest.getUsername())
            .orElseThrow(() -> new ApiException("An error occurred processing the authentication of the user."));
        String jwt = jwtService.generateJwt(user);
        return AuthenticationResponse.builder()
            .token(jwt)
            .build();
    }
    
}
