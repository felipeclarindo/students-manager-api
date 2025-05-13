package com.example.students_manager_api.auth.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/students/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/auth/users/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/login/**").permitAll()
                .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfig() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowCredentials(false); // ou true se for necessário
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
