package fr.human_booster.dorian_ferreira.printemps.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookingLoggedDTO {
    @NotNull(message = "Entrez une date de début")
    private LocalDateTime startedAt;

    @NotNull(message = "Entrez une date de fin")
    private LocalDateTime finishedAt;

    @Min(value = 1, message = "Entrez le nombre de personne")
    private int quantity;

    @NotNull(message = "Entrez un id de Gite")
    @NotBlank(message = "Entrez un id de Gite")
    private String lodgingUuid;
}
