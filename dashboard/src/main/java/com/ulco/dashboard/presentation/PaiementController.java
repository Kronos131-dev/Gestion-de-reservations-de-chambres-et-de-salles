package com.ulco.dashboard.presentation;

import com.ulco.dashboard.business.PaiementBusiness;
import com.ulco.dashboard.persistence.Paiement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class PaiementController {

    private final PaiementBusiness paiementBusiness;

    public PaiementController(PaiementBusiness paiementBusiness) {
        this.paiementBusiness = paiementBusiness;
    }

    @GetMapping("/api/paiements")
    @ResponseBody
    public Map<String, Object> getAllPaiements() {
        float totalMois = paiementBusiness.getTotalPaiementsMois();
        List<Object[]> paiementsParPrestation = paiementBusiness.getPaiementsParPrestation();
        List<Object[]> paiementsParStatut = paiementBusiness.getPaiementsParStatut();
        List<Paiement> derniersPaiements = paiementBusiness.getPaiementsRecents();

        return Map.of(
                "totalMois", totalMois,
                "paiementsParPrestation", paiementsParPrestation,
                "paiementsParStatut", paiementsParStatut,
                "derniersPaiements", derniersPaiements
        );
    }
}
