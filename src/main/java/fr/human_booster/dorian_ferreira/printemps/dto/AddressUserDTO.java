package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressUserDTO extends AddressDTO {
    @NotNull(message = "Renseignez si cette adresse est une adresse de facturation")
    private Boolean isBilled;
}
