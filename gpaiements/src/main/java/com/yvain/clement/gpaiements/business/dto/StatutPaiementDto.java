package com.yvain.clement.gpaiements.business.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatutPaiementDto {
    private Integer idStatut;
    private String code;
    private LocalDate date;
    private String description;
}