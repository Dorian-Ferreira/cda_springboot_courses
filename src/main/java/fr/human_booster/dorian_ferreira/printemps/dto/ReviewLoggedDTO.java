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
    @NotNull
    @NotBlank
    private String content;

    @Positive
    private float rating;

    @NotNull
    @NotBlank
    private String lodgingUuid;
}
