package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDTO {

    @NotNull(message = "Entrez une clé de traduction")
    @NotBlank(message = "Entrez une clé de traduction")
    private String translationKey;

    @NotNull(message = "Entrez le type de Piece")
    @NotBlank(message = "Entrez le type de Piece")
    private String type;
}
