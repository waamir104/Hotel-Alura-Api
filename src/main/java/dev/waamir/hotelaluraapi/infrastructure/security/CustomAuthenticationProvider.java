package dev.waamir.hotelaluraapi.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiNotFoundException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.UserDisabledException;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.WrongCredentialsException;
import io.micrometer.common.util.StringUtils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IUserRepository<User> userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        final String password = authentication.getCredentials().toString();
        if(StringUtils.isEmpty(username)){
            throw new WrongCredentialsException("");
        }
        UserDetails user = null;
        try {
            user = userRepository.getByUsername(username)
                .orElseThrow(() -> {
                    throw new ApiNotFoundException("User not found.");
                });
        } catch (UsernameNotFoundException e) {
            throw new WrongCredentialsException("");
        }
        if (user.isEnabled() != true){
            throw new UserDisabledException("");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongCredentialsException("");
        }
        return createSuccessfulAuthentication(authentication, user);
    }

    private Authentication createSuccessfulAuthentication(Authentication authentication, UserDetails user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
            .isAssignableFrom(authentication));
    }
    
}
