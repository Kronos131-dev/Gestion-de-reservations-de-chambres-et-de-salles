package com.example.manager.presentation.controller;

import com.example.manager.business.service.LoginService;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.example.manager.presentation.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Login", description = "Connexion utilisateur")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequestDTO("", ""));
        return "login";
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean valid = loginService.verifyCredentials(loginRequest);

        if (valid) {
            return ResponseEntity.ok(new LoginResponseDTO("FAKE_TOKEN", "Connexion réussie"));
        } else {
            return ResponseEntity.status(401).body(new LoginResponseDTO(null, "Identifiants invalides"));
        }
    }
}
