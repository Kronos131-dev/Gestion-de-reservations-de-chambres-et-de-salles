package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.UtilisateurService;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @GetMapping
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurService.findUtilisateurs();
    }

    @Autowired
    private UtilisateurService utilisateurService;
}
