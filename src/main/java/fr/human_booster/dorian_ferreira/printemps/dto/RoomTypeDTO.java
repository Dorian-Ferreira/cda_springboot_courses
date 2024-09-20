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

    @NotNull
    @NotBlank
    private String translationKey;

    @NotNull
    @NotBlank
    private String type;
}
