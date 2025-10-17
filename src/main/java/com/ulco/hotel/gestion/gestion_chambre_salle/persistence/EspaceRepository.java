package com.ulco.hotel.gestion.gestion_chambre_salle.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspaceRepository extends JpaRepository<Espace, Long> {

    @Query("SELECT COUNT(e) > 0 FROM Espace e WHERE e.typeEspace.id_type = :idType")
    boolean existsByTypeEspaceId(@Param("idType") Long idType);

    @Query("SELECT e FROM Espace e WHERE e.typeEspace.id_type = :idType")
    List<Espace> findByTypeEspaceId(@Param("idType") Long idType);
}



