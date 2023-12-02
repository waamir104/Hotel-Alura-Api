package dev.waamir.hotelaluraapi.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.waamir.hotelaluraapi.infrastructure.security.JWT.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterChainConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
        
    private static final String[] WHITE_LIST_URLS = {
        "/api/v1/user/**", "/api/v1/room/list", "/api/v1/room/number/{number}"
    };
    private static final String[] GET_ADMIN_WORKER_PATHS = {
        "api/v1/guest/list", "api/v1/guest/idNumber/{idNumber}", "api/v1/guest/email/{email}"
    };
    private static final String[] PUT_ADMIN_WORKER_PATHS = {
        "api/v1/guest/update"
    };
    private static final String[] POST_ADMIN_WORKER_PATHS = {
        "api/v1/guest/register"
    };
    private static final String[] POST_ADMIN_PATHS = {
        "/api/v1/room/register"
    };
    private static final String[] PUT_ADMIN_PATHS = {
        "api/v1/room/update"
    };

    @Bean
    public SecurityFilterChain securityFilterCHain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(
            req -> req.requestMatchers(WHITE_LIST_URLS)
                .permitAll()
                .requestMatchers(HttpMethod.POST, POST_ADMIN_PATHS).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, PUT_ADMIN_PATHS).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, POST_ADMIN_WORKER_PATHS).hasAnyAuthority("ADMIN", "WORKER")
                .requestMatchers(HttpMethod.PUT, PUT_ADMIN_WORKER_PATHS).hasAnyAuthority("ADMIN", "WORKER")
                .requestMatchers(HttpMethod.GET, GET_ADMIN_WORKER_PATHS).hasAnyAuthority("ADMIN", "WORKER")
                .anyRequest()
                .authenticated()
        );
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
