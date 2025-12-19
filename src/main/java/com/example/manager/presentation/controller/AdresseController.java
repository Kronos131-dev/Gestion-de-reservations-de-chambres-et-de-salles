package com.example.manager.presentation.controller;

import com.example.manager.business.service.AdresseService;
import com.example.manager.persistence.entity.Adresse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller REST pour gérer les adresses
@RestController
@RequestMapping("/api/adresses")
@Tag(name = "Adresses", description = "Gestion des adresses")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    // Endpoint pour récupérer toutes les adresses
    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        return ResponseEntity.ok(adresseService.getAllAdresses());
    }

    // Endpoint pour créer une nouvelle adresse
    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = adresseService.createAdresse(adresse);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdresse);
    }
}
