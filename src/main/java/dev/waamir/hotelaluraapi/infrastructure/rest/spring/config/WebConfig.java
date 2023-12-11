package dev.waamir.hotelaluraapi.infrastructure.rest.spring.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Value("${application.front-end.host}")
    private String frontHost;

    @Value("${cors.allowed.methods}")
    private List<String> methods;

    @Value("${cors.maxAge}")
    private Long maxAge;

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedMethods(methods);
        corsConfiguration.setAllowedOrigins(Arrays.asList(frontHost));
        corsConfiguration.setAllowedHeaders(Arrays.asList(
            "Origin", "Access-Controll-Allow-Origin", "Content-Type", "Accept", "Jwt-Token",
            "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Accerss-Control-Request-Headers"
        ));
        corsConfiguration.setExposedHeaders(Arrays.asList(
            "Origin", "Access-Controll-Allow-Origin", "Content-Type", "Accept", "Jwt-Token",
            "Authorization", "Access-Control-Allow-Credentials", "File-Name"
        ));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean 
    public CorsFilter corsWebFilter() {
        return new CorsFilter(corsConfiguration());
    }
}
