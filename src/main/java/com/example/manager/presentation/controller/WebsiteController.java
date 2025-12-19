package com.example.manager.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Controller pour gérer les pages web (frontend)
@Controller
public class WebsiteController {

    // Affiche la page de gestion des utilisateurs
    @GetMapping("/users")
    public String showUtilisateursPage(Model model) {
        model.addAttribute("pageTitle", "Gestion des utilisateurs");
        return "utilisateurs"; // nom de la vue (utilisateurs.html)
    }

    // Affiche la page de login
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "login"; // nom de la vue (login.html)
    }
}
