package com.yvain.clement.gpaiements.business.controller;

import com.yvain.clement.gpaiements.business.dto.PrestationDto;
import com.yvain.clement.gpaiements.business.service.IPrestationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/prestations")
@CrossOrigin
public class PrestationController {

    private final IPrestationService prestationService;

    public PrestationController(IPrestationService prestationService) {
        this.prestationService = prestationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestationDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(prestationService.getById(id));
    }

    @GetMapping("/{id}/prix")
    public ResponseEntity<BigDecimal> getPrixById(@PathVariable Integer id) {
        return  ResponseEntity.ok(prestationService.getPrixById(id));
    }
}
