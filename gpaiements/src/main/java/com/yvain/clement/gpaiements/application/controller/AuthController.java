package com.yvain.clement.gpaiements.application.controller;

import com.yvain.clement.gpaiements.business.dto.LoginRequest;
import com.yvain.clement.gpaiements.business.security.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
//Simulation avec un admin stocké dans le properties mais a terme utilsiation des admins de la bdd
public class AuthController {

    private final JwtUtils jwtUtils;

    @Value("${app.auth.admin-user}")
    private String adminUser;

    @Value("${app.auth.admin-mdp}")
    private String adminMdP;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (adminUser.equals(loginRequest.username()) && adminMdP.equals(loginRequest.password())) {
            String token = jwtUtils.generateToken(loginRequest.username());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("type", "Bearer");

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Identifiants invalides");
    }
}
