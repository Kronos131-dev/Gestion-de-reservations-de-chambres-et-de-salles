package com.yvain.clement.gpaiements.business.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaiementDto {
    private Integer idSPaiement;
    private Integer statutId;
    private BigDecimal prix;
    private LocalDate date;
}