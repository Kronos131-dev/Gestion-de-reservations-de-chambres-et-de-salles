package com.ulco.hotel.gestion.gestion_chambre_salle.presentation;

import com.ulco.hotel.gestion.gestion_chambre_salle.business.EspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espaces")
public class EspaceController {

    @Autowired
    private EspaceService espaceService;

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




    @GetMapping
    public List<Espace> getAllEspaces() {
        return espaceService.findAll();
    }


}