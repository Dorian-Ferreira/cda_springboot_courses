package fr.human_booster.dorian_ferreira.printemps.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookingDTO {
    @NotNull
    private LocalDateTime startedAt;

    @NotNull
    private LocalDateTime finishedAt;

    private int quantity;

    @NotNull
    @NotBlank
    private String userUuid;

    @NotNull
    @NotBlank
    private String lodgingUuid;
}
