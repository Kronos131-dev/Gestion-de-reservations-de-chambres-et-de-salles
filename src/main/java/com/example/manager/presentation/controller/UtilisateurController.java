package com.example.manager.presentation.controller;

import com.example.manager.business.service.UtilisateurService;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.presentation.dto.UtilisateurDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    //@PreAuthorize("#id == authentication.principal or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurDetails(id));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody UtilisateurDTO dto) {
        Utilisateur savedUtilisateur = utilisateurService.createUtilisateur(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUtilisateur);
    }

    //@PreAuthorize("#id == authentication.principal or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifyUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO dto) {
        Utilisateur modifiedUtilisateur = utilisateurService.modifyUtilisateur(id,dto);
        return ResponseEntity.ok(modifiedUtilisateur);
    }

    //@PreAuthorize("#id == authentication.principal or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès.");
    }
}
