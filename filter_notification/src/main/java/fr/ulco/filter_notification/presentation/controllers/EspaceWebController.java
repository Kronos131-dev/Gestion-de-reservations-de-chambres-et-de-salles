package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.EspaceService;
import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/espaces")
public class EspaceWebController {

    @GetMapping
    public String getEspaces(Model model, @RequestBody(required=false) EspaceFilterDTO filter) {
        model.addAttribute("espaces", espaceService.findEspaces(filter));
        return "espaces";
    }

    @Autowired
    private EspaceService espaceService;
}
