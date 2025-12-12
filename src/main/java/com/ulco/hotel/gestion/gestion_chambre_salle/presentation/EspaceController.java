package com.ulco.hotel.gestion.gestion_chambre_salle.presentation;

import com.ulco.hotel.gestion.gestion_chambre_salle.business.EspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.business.TypeEspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/espaces")
public class EspaceController {

    private final EspaceService espaceService;

    public EspaceController() {
        this.espaceService = new EspaceService();

    }

    @PostMapping
    public ResponseEntity<Espace> createEspace(@RequestBody Espace espace) {
        try {
            Espace savedEspace = espaceService.save(espace);
            return ResponseEntity.ok(savedEspace);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspace(@PathVariable Long id) {
        try {
            espaceService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Espace> updateEspace(@PathVariable Long id, @RequestBody Espace espaceModif) {
        try {
            Espace updatedEspace = espaceService.update(id, espaceModif);
            return ResponseEntity.ok(updatedEspace);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping
    public String getAllEspaces(Model model) {
        List<Espace> espaces = espaceService.findAll();
        model.addAttribute("espaces", espaces);
        model.addAttribute("espace", new Espace());
        return "espaces/list";
    }


}