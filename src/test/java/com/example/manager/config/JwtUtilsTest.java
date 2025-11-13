package com.example.manager.config;

import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JwtUtilsTest {

    private JwtUtils jwtUtils;
    private Utilisateur user;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();

        Role role = new Role();
        role.setNom("CLIENT");

        user = new Utilisateur();
        user.setId(1L);
        user.setEmail("user@test.com");
        user.setRole(role);
    }

    @Test
    void generateAndExtractClaims_shouldWork() {
        String token = jwtUtils.generateToken(user);
        assertNotNull(token, "Le token ne doit pas être null");

        String email = jwtUtils.extractEmail(token);
        assertEquals("user@test.com", email);

        String role = jwtUtils.extractRole(token);
        assertEquals("CLIENT", role);

        Long id = (long) jwtUtils.extractId(token);
        assertEquals(1L, id);

        assertTrue(jwtUtils.validateToken(token, email), "Le token devrait être valide");
    }

    @Test
    void validateExpiredToken_shouldFail() {
        // Simuler un token expiré
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().getNom())
                .claim("id", user.getId())
                .setIssuedAt(Date.from(Instant.now().minusSeconds(3600)))
                .setExpiration(Date.from(Instant.now().minusSeconds(1)))
                .compact();

        assertFalse(jwtUtils.validateToken(token, user.getEmail()));
    }

    @Test
    void validateMalformedToken_shouldFail() {
        String badToken = "abc.def.ghi";
        assertFalse(jwtUtils.validateToken(badToken, user.getEmail()));
    }
}
