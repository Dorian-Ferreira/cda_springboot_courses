package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {
    @NotNull(message = "Entrez un nom de rue")
    @NotBlank(message = "Entrez un nom de rue")
    private String street;

    private String number;

    @NotNull(message = "Entrez un code postal")
    @NotBlank(message = "Entrez un code postal")
    private String zipCode;

    @NotNull(message = "Entrez un nom de ville")
    @NotBlank(message = "Entrez un nom de ville")
    private String city;

    @NotNull(message = "Entrez un nom de pays")
    @NotBlank(message = "Entrez un nom de pays")
    private String country;

    private String more;
}
