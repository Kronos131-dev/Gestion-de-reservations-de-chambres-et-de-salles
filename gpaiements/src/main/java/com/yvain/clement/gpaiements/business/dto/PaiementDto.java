package com.yvain.clement.gpaiements.business.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


public record PaiementDto (
        Integer idPaiement,
        Integer idPrestation,
        Integer idStatut,
        BigDecimal prix,
        LocalDate date
) {}