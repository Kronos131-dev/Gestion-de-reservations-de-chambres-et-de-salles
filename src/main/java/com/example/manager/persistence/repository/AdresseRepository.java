package com.example.manager.persistence.repository;

import com.example.manager.persistence.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository pour gérer les adresses en base de données
// Fournit les opérations CRUD grâce à JpaRepository
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {}
