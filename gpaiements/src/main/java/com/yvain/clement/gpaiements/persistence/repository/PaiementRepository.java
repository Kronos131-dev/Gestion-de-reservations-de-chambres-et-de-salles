package com.yvain.clement.gpaiements.persistence.repository;

import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRepository extends JpaRepository<PaiementEntity, Integer>{
    List<PaiementEntity> findByStatus(PaiementStatutEntity statut);

}