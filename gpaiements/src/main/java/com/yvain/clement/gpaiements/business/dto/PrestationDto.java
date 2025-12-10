package com.yvain.clement.gpaiements.business.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

public record PrestationDto (
        Integer idPrestation,
        Integer reservationId,
        Integer paiementId,
        BigDecimal prix
) {}