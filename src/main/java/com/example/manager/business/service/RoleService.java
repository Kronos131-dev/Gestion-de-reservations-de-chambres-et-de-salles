package com.example.manager.business.service;

import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service métier pour gérer les rôles
@Service
@RequiredArgsConstructor
public class RoleService {

    // Repository pour accéder aux rôles en base de données
    @Autowired
    private RoleRepository roleRepository;

    // Récupère la liste de tous les rôles
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
