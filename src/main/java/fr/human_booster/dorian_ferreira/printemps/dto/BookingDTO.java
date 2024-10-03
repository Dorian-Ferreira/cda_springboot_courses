package fr.human_booster.dorian_ferreira.printemps.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO extends BookingLoggedDTO {
    @NotNull(message = "Entrez un id d'utilisateur")
    @NotBlank(message = "Entrez un id d'utilisateur")
    private String userUuid;
}
