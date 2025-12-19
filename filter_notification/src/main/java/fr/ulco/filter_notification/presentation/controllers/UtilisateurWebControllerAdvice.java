package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.UtilisateurService;
import fr.ulco.filter_notification.presentation.dto.UtilisateurDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// Renvoie l'attribut "utilisateur" vers toutes les classes
// ayant une annotation @Controller (ici EspaceWebController)
@ControllerAdvice(annotations = Controller.class)
public class UtilisateurWebControllerAdvice {

    @ModelAttribute("utilisateur")
    public UtilisateurDTO getFirstUtilisateur(Model model) {
        return utilisateurService.findFirstUtilisateur();
    }

    @Autowired
    private UtilisateurService utilisateurService;
}
