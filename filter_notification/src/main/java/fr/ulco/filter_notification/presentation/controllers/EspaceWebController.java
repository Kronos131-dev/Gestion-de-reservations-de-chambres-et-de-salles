package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.EspaceService;
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

    @GetMapping
    public String getEspaces(Model model, @ModelAttribute(name="filter") EspaceFilterDTO filter) {
        model.addAttribute("espaces", espaceService.findEspaces(filter));
        return "espaces";
    }

    @PutMapping("/{id}")
    public String updateEspaceStatus(Model model,
                                     @PathVariable Long id,
                                     @ModelAttribute EspaceUpdateDTO dto) {
        model.addAttribute("updatedEspace", espaceService.updateEspaceStatus(id, dto));
        return "espaces";
    }

    @Autowired
    private EspaceService espaceService;
}
