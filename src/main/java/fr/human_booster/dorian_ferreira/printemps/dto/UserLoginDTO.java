package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @Email(message = "Entrez un email valide")
    @NotNull(message = "Entrez un email valide")
    @NotBlank(message = "Entrez un email valide")
    private String email;

    @NotNull(message = "Entrez un mot de passe")
    @NotBlank(message = "Entrez un mot de passe")
    private String password;

}
