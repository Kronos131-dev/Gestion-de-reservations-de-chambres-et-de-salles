package com.example.manager.persistence.repository;

import com.example.manager.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository pour gérer les rôles en base de données
// Fournit les opérations CRUD grâce à JpaRepository
public interface RoleRepository extends JpaRepository<Role, Long> {}
