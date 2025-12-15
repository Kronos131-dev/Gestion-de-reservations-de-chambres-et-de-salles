package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.services.EspaceService;
import fr.ulco.filter_notification.presentation.dto.EspaceDTO;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espaces")
public class EspaceController {

    @GetMapping
    public List<EspaceDTO> getEspaces(@RequestBody(required=false) EspaceFilterDTO filter) {
        return espaceService.findEspaces(filter);
    }

    @Autowired
    private EspaceService espaceService;
};
