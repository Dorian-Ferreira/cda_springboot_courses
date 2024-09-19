package fr.human_booster.dorian_ferreira.printemps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LodgingUpdateDTO {
    private String name;

    private String description;

    private int capacity;

    private boolean isAccessible;

    private int nightPrice;
}
