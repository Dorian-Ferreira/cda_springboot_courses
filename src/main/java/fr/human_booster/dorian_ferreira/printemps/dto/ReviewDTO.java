package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO extends ReviewLoggedDTO {
    @NotNull(message = "Entrez un id d'utilisateur")
    @NotBlank(message = "Entrez un id d'utilisateur")
    private String userUuid;
}
