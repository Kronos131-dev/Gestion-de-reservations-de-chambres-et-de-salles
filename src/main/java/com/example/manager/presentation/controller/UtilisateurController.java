package com.example.manager.presentation.controller;

import com.example.manager.business.service.UtilisateurService;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.presentation.dto.UtilisateurDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller REST pour gérer les utilisateurs
@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Endpoint pour récupérer tous les utilisateurs (accessible uniquement aux ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    // Endpoint pour récupérer un utilisateur par id
    // Accessible à l'utilisateur lui-même ou aux ADMIN
    @PreAuthorize("#id == authentication.principal or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurDetails(id));
    }

    // Endpoint pour créer un nouvel utilisateur (accessible uniquement aux ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody UtilisateurDTO dto) {
        UtilisateurDTO savedUtilisateur = utilisateurService.createUtilisateur(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUtilisateur);
    }

    // Endpoint pour modifier un utilisateur
    // Accessible à l'utilisateur lui-même ou aux ADMIN
    @PreAuthorize("#id == authentication.principal or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> modifyUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO dto) {
        UtilisateurDTO modifiedUtilisateur = utilisateurService.modifyUtilisateur(id, dto);
        return ResponseEntity.ok(modifiedUtilisateur);
    }

    // Endpoint pour supprimer un utilisateur
    // Accessible à l'utilisateur lui-même ou aux ADMIN
    @PreAuthorize("#id == authentication.principal or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès.");
    }
}
