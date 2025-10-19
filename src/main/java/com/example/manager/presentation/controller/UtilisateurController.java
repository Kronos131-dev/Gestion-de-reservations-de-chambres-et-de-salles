package com.example.manager.presentation.controller;

import com.example.manager.business.service.UtilisateurService;
import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.presentation.dto.UtilisateurDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurDetails(id));
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody UtilisateurDTO dto) {
        Utilisateur savedUtilisateur = utilisateurService.createUtilisateur(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUtilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifyUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO dto) {
        Utilisateur modifiedUtilisateur = utilisateurService.modifyUtilisateur(id,dto);
        return ResponseEntity.ok(modifiedUtilisateur);
    }
}
