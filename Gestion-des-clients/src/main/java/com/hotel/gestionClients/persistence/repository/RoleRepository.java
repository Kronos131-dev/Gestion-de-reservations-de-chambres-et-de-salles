package com.hotel.gestionClients.persistence.repository;

import com.hotel.gestionClients.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNom(String nom);
}