package com.yvain.clement.gpaiements.application.controller;

import com.yvain.clement.gpaiements.business.dto.PaiementStatutDto;
import com.yvain.clement.gpaiements.business.service.IPaiementStatutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/statuts")
@CrossOrigin
public class PaiementStatutController {

    private final IPaiementStatutService paiementStatutService;

    public PaiementStatutController(IPaiementStatutService paiementStatutService) {
        this.paiementStatutService = paiementStatutService;
    }

    @GetMapping
    public ResponseEntity<List<PaiementStatutDto>> getAll() {
        return ResponseEntity.ok(paiementStatutService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaiementStatutDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(paiementStatutService.getById(id));
    }
}
