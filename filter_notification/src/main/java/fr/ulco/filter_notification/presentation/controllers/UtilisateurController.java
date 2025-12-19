package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.business.observers.concrete.UtilisateurUpdateObserver;
import fr.ulco.filter_notification.business.services.UtilisateurService;
import fr.ulco.filter_notification.presentation.dto.UtilisateurDTO;
import fr.ulco.filter_notification.presentation.dto.UtilisateurUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @GetMapping
    public List<UtilisateurDTO> getUtilisateurs() {
        return utilisateurService.findUtilisateurs();
    }

    @PutMapping("/{id}")
    public UtilisateurUpdateDTO updateUtilisateur(@PathVariable Long id,
                                                           @RequestBody UtilisateurUpdateDTO dto){
        return utilisateurService.updateUtilisateur(id, dto);
    }

    @Autowired
    private UtilisateurService utilisateurService;
}
