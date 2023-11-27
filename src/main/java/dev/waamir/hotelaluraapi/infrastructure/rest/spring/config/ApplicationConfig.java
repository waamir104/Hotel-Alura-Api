package dev.waamir.hotelaluraapi.infrastructure.rest.spring.config;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Encoder base64Encoder() {
        return Base64.getEncoder();
    }

    @Bean 
    public Decoder base64Decoder() {
        return Base64.getDecoder();
    }
}
