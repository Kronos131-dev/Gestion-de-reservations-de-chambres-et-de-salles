package com.example.manager.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Configuration principale de la sécurité Spring
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // Filtre JWT personnalisé
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthenticationFilter) {
        this.jwtAuthFilter = jwtAuthenticationFilter;
    }

    // Configuration de la chaîne de filtres de sécurité
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // Désactive la protection CSRF (API stateless)
                .csrf(AbstractHttpConfigurer::disable)

                // Désactive les sessions (JWT uniquement)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Configuration des accès aux routes
                .authorizeHttpRequests(auth -> auth
                        // Routes accessibles sans authentification
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                //"/api/adresses/**",
                                //"/api/utilisateurs/**",
                                //"/api/roles/**",
                                "/api/login/**",
                                "/users",
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // Toutes les autres routes nécessitent un token
                        .anyRequest().authenticated()
                )

                // Désactive le login par formulaire
                .formLogin(AbstractHttpConfigurer::disable)

                // Désactive l'authentification HTTP Basic
                .httpBasic(AbstractHttpConfigurer::disable)

                // Ajoute le filtre JWT avant celui de Spring Security
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // Gestion des erreurs d'authentification
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(
                                        HttpServletResponse.SC_UNAUTHORIZED,
                                        "Unauthorized"
                                )
                        )
                );

        return http.build();
    }

    // Encodeur de mot de passe (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
