package com.example.manager.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// Filtre JWT utilisé pour authentifier les requêtes avec un token
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    // Classe utilitaire pour gérer les JWT
    private final JwtUtils jwtUtils;

    public JwtAuthFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Récupère le header Authorization
        final String authHeader = request.getHeader("Authorization");

        // Si aucun token ou mauvais format, on continue sans authentification
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Récupération du token sans le préfixe "Bearer "
        final String token = authHeader.substring(7); // Remove "Bearer "
        try {
            // Extraction des informations contenues dans le token
            String email = jwtUtils.extractEmail(token);
            int userId = jwtUtils.extractId(token);
            String role = jwtUtils.extractRole(token);

            // Vérifie si le token est valide
            if (email == null || !jwtUtils.validateToken(token, email)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
                return;
            }

            // Crée l'authentification si l'utilisateur n'est pas déjà authentifié
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_" + role);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userId,                 // id de l'utilisateur
                                null,                   // pas de mot de passe
                                Collections.singleton(authority)
                        );

                // Ajoute les détails de la requête
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Stocke l'utilisateur dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        } catch (ExpiredJwtException e) {
            // Token expiré
            sendUnauthorizedResponse(response, "Token expired");
            return;

        } catch (SecurityException | MalformedJwtException | IllegalArgumentException e) {
            // Token invalide ou mal formé
            sendUnauthorizedResponse(response, "Invalid token");
            return;

        } catch (Exception e) {
            // Autre erreur d'authentification
            sendUnauthorizedResponse(response, "Unauthorized");
            return;
        }

        // Passe au filtre suivant
        filterChain.doFilter(request, response);
    }

    // Envoie une réponse 401 avec un message simple
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
