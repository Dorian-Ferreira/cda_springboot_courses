package fr.human_booster.dorian_ferreira.printemps.dto;

import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    @NotNull
    @NotBlank
    private String content;

    @Positive
    private float rating;

    @NotNull
    @NotBlank
    private String userUuid;

    @NotNull
    @NotBlank
    private String lodgingUuid;
}
