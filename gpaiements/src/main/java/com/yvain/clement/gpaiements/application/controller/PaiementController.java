package com.yvain.clement.gpaiements.application.controller;


import com.yvain.clement.gpaiements.business.dto.PaiementDto;
import com.yvain.clement.gpaiements.business.service.IPaiementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/paiements")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
public class PaiementController {

    private final IPaiementService paiementService;

    public PaiementController(IPaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    public ResponseEntity<PaiementDto> create(@RequestBody PaiementDto dto) {
        PaiementDto paiementDto = paiementService.create(dto);
        return new ResponseEntity<>(paiementDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<PaiementDto> updateStatut(
            @PathVariable Integer id,
            @RequestParam Integer statutId) {
        return ResponseEntity.ok(paiementService.updateStatut(id, statutId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaiementDto> getById  (@PathVariable Integer id) {
        return ResponseEntity.ok(paiementService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaiementDto>> getAll() {
        return ResponseEntity.ok(paiementService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaiementDto> delete(@PathVariable Integer id) {
        paiementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
