package com.example.manager.config;

import com.example.manager.persistence.entity.Utilisateur;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

// Classe utilitaire pour gérer les tokens JWT
@Component
public class JwtUtils {

    // Clé secrète utilisée pour signer les tokens
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Durée de validité du token (1 heure)
    private final long expirationMs = 3600000; // 1h

    // Génère un token JWT à partir d'un utilisateur
    public String generateToken(Utilisateur user) {

        // Informations ajoutées dans le token
        Map<String, Object> claims = Map.of(
                "role", user.getRole().getNom(),
                "id", user.getId()
        );

        return Jwts.builder()
                .setClaims(claims)                      // données personnalisées
                .setSubject(user.getEmail())            // email de l'utilisateur
                .setIssuedAt(new Date())                // date de création
                .setExpiration(
                        new Date(System.currentTimeMillis() + expirationMs)
                )                                       // date d'expiration
                .signWith(key)                          // signature du token
                .compact();
    }

    // Extrait l'email depuis le token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Extrait le rôle depuis le token
    public String extractRole(String token) {
        return (String) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }

    // Extrait l'id utilisateur depuis le token
    public int extractId(String token) {
        return (int) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id");
    }

    // Vérifie si le token est valide et non expiré
    public boolean validateToken(String token, String email) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String tokenEmail = claims.getSubject();
            Date expiration = claims.getExpiration();

            // Email correct et token non expiré
            return (tokenEmail.equals(email) && expiration.after(new Date()));

        } catch (ExpiredJwtException | UnsupportedJwtException
                 | MalformedJwtException | IllegalArgumentException e) {
            // Token invalide ou expiré
            return false;
        }
    }
}
