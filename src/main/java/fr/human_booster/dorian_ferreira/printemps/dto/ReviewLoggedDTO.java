package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLoggedDTO {
    @NotNull(message = "Entrez un contenu")
    @NotBlank(message = "Entrez un contenu")
    private String content;

    @Positive(message = "La note doit etre positive")
    private float rating;

    @NotNull(message = "Entrez un id de Gite")
    @NotBlank(message = "Entrez un id de Gite")
    private String lodgingUuid;
}
