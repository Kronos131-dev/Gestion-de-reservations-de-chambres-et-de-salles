package com.example.manager.presentation.controller;

import com.example.manager.business.service.AdresseService;
import com.example.manager.persistence.entity.Adresse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adresses")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        return ResponseEntity.ok(adresseService.getAllAdresses());
    }
}
