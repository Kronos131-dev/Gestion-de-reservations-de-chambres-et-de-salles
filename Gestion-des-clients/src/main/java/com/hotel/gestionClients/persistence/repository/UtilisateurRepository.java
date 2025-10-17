package com.hotel.gestionClients.persistence.repository;

import com.hotel.gestionClients.persistence.entity.Role;
import com.hotel.gestionClients.persistence.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByRole(Role role);
}