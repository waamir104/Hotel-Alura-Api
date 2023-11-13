package dev.waamir.hotelaluraapi.infrastructure.rest.spring.config;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Encoder base64Encoder() {
        return Base64.getEncoder();
    }

    @Bean 
    public Decoder base64Decoder() {
        return Base64.getDecoder();
    }
}
