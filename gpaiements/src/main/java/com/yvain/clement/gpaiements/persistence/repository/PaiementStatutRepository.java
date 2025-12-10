package com.yvain.clement.gpaiements.persistence.repository;

import com.yvain.clement.gpaiements.persistence.model.PaiementStatutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementStatutRepository extends JpaRepository<PaiementStatutEntity, Integer>{

    List<PaiementStatutEntity> findByCode(Integer code);

    List<PaiementStatutEntity> findByDescriptionContaining(String mot);

}