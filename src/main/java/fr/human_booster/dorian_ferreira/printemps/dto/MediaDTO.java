package fr.human_booster.dorian_ferreira.printemps.dto;

import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO {

    @NotNull
    @NotBlank
    private String path;

    @NotNull
    @NotBlank
    private String extension;
}
