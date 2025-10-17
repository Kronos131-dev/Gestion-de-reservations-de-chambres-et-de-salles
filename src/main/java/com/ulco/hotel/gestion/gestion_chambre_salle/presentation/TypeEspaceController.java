package com.ulco.hotel.gestion.gestion_chambre_salle.presentation;

import com.ulco.hotel.gestion.gestion_chambre_salle.business.TypeEspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.TypeEspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types-espace")
public class TypeEspaceController {

    @Autowired
    private TypeEspaceService typeEspaceService;

    @PostMapping
    public ResponseEntity<TypeEspace> createTypeEspace(@RequestBody TypeEspace typeEspace) {
        try {
            TypeEspace savedTypeEspace = typeEspaceService.save(typeEspace);
            return ResponseEntity.ok(savedTypeEspace);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeEspace(@PathVariable Long id) {
        try {
            typeEspaceService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public List<TypeEspace> getAllTypeEspaces() {
        return typeEspaceService.findAll();
    }

}
