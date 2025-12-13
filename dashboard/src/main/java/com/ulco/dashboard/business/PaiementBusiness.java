package com.ulco.dashboard.business;

import com.ulco.dashboard.persistence.Paiement;
import com.ulco.dashboard.persistence.PaiementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaiementBusiness {

    private final PaiementRepository paiementRepository;

    public PaiementBusiness(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public List<Paiement> getAllPaiements() {
        return paiementRepository.getAllPaiements();
    }

    // Total des paiements du mois
    public float getTotalPaiementsMois() {
        Float total = paiementRepository.getTotalPaiementsDuMois();
        return total != null ? total : 0f;
    }

    // 5 derniers paiements
    public List<Paiement> getPaiementsRecents() {
        return paiementRepository.getDerniersPaiements()
                .stream()
                .limit(5)
                .toList();
    }

    // Paiements par prestation
    public List<Object[]> getPaiementsParPrestation() {
        return paiementRepository.getPaiementsParPrestation();
    }

    // Paiements par statut
    public List<Object[]> getPaiementsParStatut() {
        return paiementRepository.getPaiementsParStatut();
    }
}
