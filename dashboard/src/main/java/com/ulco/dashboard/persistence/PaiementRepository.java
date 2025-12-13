package com.ulco.dashboard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    @Query("SELECT p FROM Paiement p")
    List<Paiement> getAllPaiements();

    @Query("SELECT p FROM Paiement p " +
            "LEFT JOIN p.prestation pr " +
            "LEFT JOIN pr.reservation r " +
            "ORDER BY r.date_arrivee DESC")
    List<Paiement> getDerniersPaiements();


    @Query("SELECT SUM(p.prix) FROM Paiement p " +
            "WHERE EXTRACT(MONTH FROM p.prestation.reservation.date_arrivee) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "AND EXTRACT(YEAR FROM p.prestation.reservation.date_arrivee) = EXTRACT(YEAR FROM CURRENT_DATE)")
    Float getTotalPaiementsDuMois();

    @Query("SELECT p.prestation.id_prestation, SUM(p.prix) FROM Paiement p GROUP BY p.prestation.id_prestation")
    List<Object[]> getPaiementsParPrestation();

    @Query("SELECT p.statut.code, COUNT(p) FROM Paiement p GROUP BY p.statut.code")
    List<Object[]> getPaiementsParStatut();
}
