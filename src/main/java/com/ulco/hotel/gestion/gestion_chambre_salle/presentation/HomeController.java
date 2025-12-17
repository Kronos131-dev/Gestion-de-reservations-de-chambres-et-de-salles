package com.ulco.hotel.gestion.gestion_chambre_salle.presentation;

import com.ulco.hotel.gestion.gestion_chambre_salle.business.EspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.business.TypeEspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EspaceService espaceService;

    @Autowired
    private TypeEspaceService typeEspaceService;

    @GetMapping("/")
    public String home(Model model) {
        List<Espace> allEspaces = espaceService.findAll();
        List<TypeEspace> allTypes = typeEspaceService.findAll();

        model.addAttribute("espaces", allEspaces);
        model.addAttribute("types", allTypes);

        return "index";
    }
}