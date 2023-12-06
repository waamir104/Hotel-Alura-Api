package dev.waamir.hotelaluraapi.infrastructure.rest.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Value("${application.front-end.host}")
    private String frontHost;

    @Value("${cors.allowed.headers}")
    private String[] headers;

    @Value("${cors.allowed.methods}")
    private String[] methods;

    @Value("${cors.maxAge}")
    private Long maxAge;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                .addMapping("/**")
                // Change to the frontHost
                .allowedOrigins("*")
                .allowedHeaders(headers)
                .allowedMethods(methods)
                .allowCredentials(true)
                .maxAge(maxAge);
            }
        };
    }
}
