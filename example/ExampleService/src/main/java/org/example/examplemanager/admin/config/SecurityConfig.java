package org.example.examplemanager.admin.config;

import org.example.examplemanager.admin.jwt.CustomJwt;
import org.example.examplemanager.admin.jwt.CustomJwtConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
//    https://www.youtube.com/watch?v=DLszg2ul85U
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(customJwtConverter())
                ));
        return http.build();
    }

    private Converter<Jwt, CustomJwt> customJwtConverter() {
        return new CustomJwtConverter();
    }
}
