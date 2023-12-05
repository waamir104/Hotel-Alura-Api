package dev.waamir.hotelaluraapi.infrastructure.rest.spring.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Value("${application.front-end.host}")
    private String frontHost;

    @Value("${cors.allowed.headers}")
    private List<String> headers;

    @Value("${cors.allowed.methods}")
    private List<String> methods;

    @Value("${cors.maxAge}")
    private Long maxAge;
    
    @Bean 
    public CorsConfigurationSource corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(frontHost);
        config.setAllowedHeaders(headers);
        config.setAllowedMethods(methods);
        config.setMaxAge(maxAge);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
