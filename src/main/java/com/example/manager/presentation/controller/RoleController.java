package com.example.manager.presentation.controller;

import com.example.manager.business.service.RoleService;
import com.example.manager.persistence.entity.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controller REST pour gérer les rôles
@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Gestion des rôles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Endpoint pour récupérer tous les rôles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRole() {
        return ResponseEntity.ok(roleService.getAllRole());
    }
}
