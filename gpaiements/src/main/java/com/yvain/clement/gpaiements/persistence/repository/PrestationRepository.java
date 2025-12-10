package com.yvain.clement.gpaiements.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yvain.clement.gpaiements.persistence.model.PrestationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestationRepository extends JpaRepository<PrestationEntity, Integer>{

    List<PrestationEntity> findByPaiementId(Integer paiementId);
}