package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @Email(message = "Entrez un email valide")
    @NotNull(message = "Entrez un email valide")
    @NotBlank(message = "Entrez un email valide")
    private String email;

    @Past(message = "Entrez une date de naissance dans le passé")
    @NotNull(message = "Entrez une date de naissance dans le passé")
    private LocalDate birthAt;

    @NotNull(message = "Entrez une prénom")
    @NotBlank(message = "Entrez une prénom")
    private String firstName;

    @NotNull(message = "Entrez une nom de famille")
    @NotBlank(message = "Entrez une nom de famille")
    private String lastName;

    @NotNull(message = "Entrez un mot de passe")
    @NotBlank(message = "Entrez un mot de passe")
    private String password;

    @NotNull(message = "Entrez un mot de passe")
    @NotBlank(message = "Entrez un mot de passe")
    private String confirmPassword;
}
