package com.hotel.gestionTarifs.persistence.repository;

import com.hotel.gestionTarifs.persistence.entity.Extra;
import com.hotel.gestionTarifs.persistence.entity.ExtraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, ExtraId> {
}
