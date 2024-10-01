package fr.human_booster.dorian_ferreira.printemps.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LodgingSearchDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer nightPrice;

    private Integer capacity;

    private Boolean isAccessible;
}
