package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.EspaceService;
import fr.ulco.filter_notification.persistence.entities.Espace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espaces")
public class EspaceController {

    @GetMapping
    public List<Espace> getAllEspaces() {
        return espaceService.findAll();
    }

    @Autowired
    private EspaceService espaceService;

};
