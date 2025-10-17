package com.hotel.gestionTarifs.persistence.repository;

import com.hotel.gestionTarifs.persistence.entity.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PrestationRepository extends JpaRepository<Prestation,Integer> {
}