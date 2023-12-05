package dev.waamir.hotelaluraapi.application.service.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static dev.waamir.hotelaluraapi.application.enumeration.RoleType.*;

import java.time.LocalDateTime;

import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Auth.AuthRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Auth.AuthResetPwdRequest;
import dev.waamir.hotelaluraapi.adapter.dto.resource.Auth.AuthResponse;
import dev.waamir.hotelaluraapi.application.model.EmailDetails;
import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IEmailService;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.GenericException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.UserDisabledException;
import dev.waamir.hotelaluraapi.infrastructure.security.JWT.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthResourceService {

    @Value("${application.front-end.host}")
    private String frontHost;

    private final IUserRepository<User> userRepository;
    private final IRoleRepository<Role> roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final IEmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(AuthRequest userRequest) {
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
        return AuthResponse.builder()
            .token(jwt)
            .role(user.getRole().getName())
            .build();
    }

    public AuthResponse authenticate(AuthRequest userRequest) {
        if (userRepository.getUsernameCount(userRequest.getUsername()) != 1) throw new ApiNotFoundException("User not found.");
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(),
                userRequest.getPassword()
            )
        );
        User user = userRepository.getByUsername(userRequest.getUsername())
            .orElseThrow(() -> new ApiException("An error occurred processing the authentication of the user."));
        String jwt = jwtService.generateJwt(user);
        return AuthResponse.builder()
            .token(jwt)
            .role(user.getRole().getName())
            .build();
    }
    
    public MessageResponse verify(String type, String userIdEncoded, String url) {
        userRepository.enable(type, userIdEncoded, url);
        return MessageResponse.builder()
            .message("Account verified")
            .build();
    }

    public MessageResponse resetPwdRequest(String username) {
        User user = userRepository.getByUsername(username).orElseThrow(() -> {
            throw new ApiNotFoundException("User not found.");
        });
        if (user.isEnabled() != true) throw new UserDisabledException("");
        String jwt = jwtService.generateJwt(user);
        String url = frontHost.concat(String.format("/resetPassword?token=%s", jwt));
        EmailDetails emailDetails = EmailDetails.builder()
            .recipient(username)
            .subject("Reset Password")
            .msgBody(emailService.getResetPwdMessage(url, user))
            .build();
        emailService.sendEmail(emailDetails);
        return MessageResponse.builder()
            .message("Reset Password request successfully created.")
            .build();
    }

    public MessageResponse resetPwd(AuthResetPwdRequest request) {
        User user = userRepository.getByUsername(request.username()).orElseThrow(() -> {
            throw new ApiNotFoundException("User not found.");
        });
        if (!jwtService.isTokenValid(request.token(), user)) throw new GenericException("Token invalid.", HttpStatus.BAD_REQUEST);
        if (!request.password1().equals(request.password2())) throw new GenericException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        user.setPassword(passwordEncoder.encode(request.password1()));
        userRepository.update(user);
        EmailDetails emailDetails = EmailDetails.builder()
            .recipient(user.getUsername())
            .subject("Reset Confirmation")
            .msgBody(emailService.getResetPwdConfirmationMessage())
            .build();
        emailService.sendEmail(emailDetails);
        return MessageResponse.builder()
                .message("Password reset successfully.")
                .build();
    }
}
