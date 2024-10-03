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
    @NotNull(message = "Entrez un nom")
    @NotBlank(message = "Entrez un nom")
    private String name;

    private String description;

    @Min(value = 1, message = "Entrez la capacité")
    private int capacity;

    @NotNull(message = "Renseignez si le gite est accessible")
    private Boolean isAccessible;

    @Min(value = 1, message = "Entrez le prix de la nuit en centimes")
    private int nightPrice;
}
