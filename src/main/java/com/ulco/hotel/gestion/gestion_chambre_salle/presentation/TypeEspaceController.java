package com.ulco.hotel.gestion.gestion_chambre_salle.presentation;

import com.ulco.hotel.gestion.gestion_chambre_salle.business.TypeEspaceService;
import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.Espace;
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
    public ResponseEntity<?> deleteTypeEspace(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean deleteEspaces) {
        try {
            typeEspaceService.deleteById(id, deleteEspaces);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<TypeEspace> getAllTypeEspaces() {
        return typeEspaceService.findAll();
    }

    @GetMapping("/{id}/espaces")
    public ResponseEntity<List<Espace>> getEspacesAssocies(@PathVariable Long id) {
        try {
            List<Espace> espaces = typeEspaceService.getEspacesAssocies(id);
            return ResponseEntity.ok(espaces);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
