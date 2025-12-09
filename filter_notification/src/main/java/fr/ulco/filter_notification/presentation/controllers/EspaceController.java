package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.EspaceService;
import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/espaces")
public class EspaceController {

    @GetMapping
    public List<Espace> getAllEspaces() {
        return espaceService.findAll();
    }

    @GetMapping("/filter")
    public List<Espace> getFilteredEspaces(@RequestBody EspaceFilterDTO filter) {
        return espaceService.findFiltered(filter);
    }

    @Autowired
    private EspaceService espaceService;

};
