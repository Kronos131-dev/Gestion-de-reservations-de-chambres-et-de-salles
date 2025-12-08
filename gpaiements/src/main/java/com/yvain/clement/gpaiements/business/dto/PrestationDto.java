package com.yvain.clement.gpaiements.business.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDto {
    private Integer idPrestation;
    private Integer reservationId;
    private LocalDate paiementId;
    private BigDecimal prix;
}