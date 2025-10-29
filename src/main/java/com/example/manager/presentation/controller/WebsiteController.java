package com.example.manager.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {

    @GetMapping("/users")
    public String showUtilisateursPage(Model model) {
        model.addAttribute("pageTitle", "Gestion des utilisateurs");
        return "utilisateurs";
    }
}
