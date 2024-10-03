package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    @NotNull(message = "Entrez une prénom")
    @NotBlank(message = "Entrez une prénom")
    private String firstName;

    @NotNull(message = "Entrez une nom de famille")
    @NotBlank(message = "Entrez une nom de famille")
    private String lastName;

    @NotNull(message = "Entrez un numéro de téléphone")
    @NotBlank(message = "Entrez un numéro de téléphone")
    private String phone;

    private String photo;
}
