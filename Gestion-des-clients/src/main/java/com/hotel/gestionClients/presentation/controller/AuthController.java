package com.hotel.gestionClients.presentation.controller;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.business.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/inscription")
    public String inscriptionForm(Model model) {
        model.addAttribute("utilisateur", new UtilisateurDTO());
        return "auth/register";
    }

    @PostMapping("/inscription")
    public String inscriptionSubmit(@ModelAttribute UtilisateurDTO utilisateurDTO) {
        utilisateurService.createClient(utilisateurDTO);
        return "redirect:/login?success";
    }
}