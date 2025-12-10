package com.yvain.clement.gpaiements.persistence.repository;

import com.yvain.clement.gpaiements.persistence.model.PaiementEntity;
import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<PaiementEntity, Integer>{

    List<PaiementEntity> findByStatut(PaiementStatutEntity statut);

    List<PaiementEntity> findByStatutId(Integer statutId);

    List<PaiementEntity> findByDate(LocalDate date);
    List<PaiementEntity> findByDateBetween(LocalDate debut, LocalDate fin);

}