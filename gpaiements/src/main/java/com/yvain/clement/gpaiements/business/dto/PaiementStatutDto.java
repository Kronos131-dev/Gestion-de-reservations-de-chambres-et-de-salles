package com.yvain.clement.gpaiements.business.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

public record PaiementStatutDto(
        Integer idStatut,
        Integer code,
        String description
) {}