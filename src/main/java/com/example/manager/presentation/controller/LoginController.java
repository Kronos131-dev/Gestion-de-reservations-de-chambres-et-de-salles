package com.example.manager.presentation.controller;

import com.example.manager.business.service.LoginService;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.example.manager.presentation.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        Optional<String> token = loginService.verifyCredentials(loginRequest);

        if (token.isPresent()) {
            return ResponseEntity.ok(new LoginResponseDTO(token, "Connexion réussie"));
        } else {
            return ResponseEntity.status(401).body(new LoginResponseDTO(null, "Identifiants invalides"));
        }
    }
}
