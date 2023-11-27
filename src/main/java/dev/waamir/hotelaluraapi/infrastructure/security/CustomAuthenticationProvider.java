package dev.waamir.hotelaluraapi.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
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
            throw new BadCredentialsException("");
        }
        UserDetails user = null;
        try {
            user = userRepository.getByUsername(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("");
                });
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("");
        }
        if (user.isEnabled() != true){
            throw new DisabledException("");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("");
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
