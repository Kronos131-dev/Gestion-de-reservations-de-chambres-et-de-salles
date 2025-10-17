package com.hotel.gestionTarifs.persistence.repository;

import com.hotel.gestionTarifs.persistence.entity.ServiceExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceExtraRepository extends JpaRepository<ServiceExtra, Integer> {
}
