package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LodgingCreateDTO {
    private String name;

    private int capacity;

    private boolean isAccessible;

    private int nightPrice;

    private String slug;
}
