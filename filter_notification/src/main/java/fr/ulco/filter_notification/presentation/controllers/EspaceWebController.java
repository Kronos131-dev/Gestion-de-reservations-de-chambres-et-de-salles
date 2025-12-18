package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.EspaceService;
import fr.ulco.filter_notification.business.services.UtilisateurService;
import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.presentation.dto.EspaceDTO;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import fr.ulco.filter_notification.presentation.dto.EspaceUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/espaces")
public class EspaceWebController {

    @Autowired
    private EspaceService espaceService;
    
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public String getEspaces(Model model, @ModelAttribute(name="filter") EspaceFilterDTO filter) {
        model.addAttribute("espaces", espaceService.findEspaces(filter));
        model.addAttribute("utilisateur", utilisateurService.findFirstUtilisateur());
        model.addAttribute("filter", filter);
        return "espaces";
    }

    @PostMapping("/{id}")
    public String updateEspaceStatus(@PathVariable Long id,
                                     @ModelAttribute EspaceUpdateDTO dto) {
        espaceService.updateEspaceStatus(id, dto);
        return "redirect:/espaces";
    }
}