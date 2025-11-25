package com.ulco.dashboard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EspaceRepository extends JpaRepository<Espace, Long> {

    @Query("SELECT e.status FROM Espace e ")
    List<Espace.Status> getAllStatus();

    @Query("SELECT e.prix_base FROM Espace e")
    List<Float> getAllEspacesPrices();

    @Query("SELECT e.nb_place FROM Espace e")
    List<Float> getAllEspacesPlaces();

    @Query("SELECT e.typeEspace,COUNT(e) FROM Espace e GROUP BY e.typeEspace")
    List<Object[]> getAllEspacesTypesAndCount();
}
