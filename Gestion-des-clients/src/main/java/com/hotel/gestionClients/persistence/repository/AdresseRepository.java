package com.hotel.gestionClients.persistence.repository;

import com.hotel.gestionClients.persistence.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}