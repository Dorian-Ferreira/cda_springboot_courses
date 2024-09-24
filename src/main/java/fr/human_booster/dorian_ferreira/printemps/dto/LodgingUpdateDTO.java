package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgingUpdateDTO {
    @NotNull
    @NotBlank
    private String name;

    private String description;

    @Min(1)
    private int capacity;

    @NotNull
    private Boolean isAccessible;

    @Min(1)
    private int nightPrice;
}
