package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("utilisateurs")
public class UtilisateurWebController {

    @GetMapping
    public String getFirstUtilisateur(Model model) {
        model.addAttribute("utilisateur", utilisateurService.findFirstUtilisateur());
        return "espaces";
    }

    @Autowired
    private UtilisateurService utilisateurService;
}
