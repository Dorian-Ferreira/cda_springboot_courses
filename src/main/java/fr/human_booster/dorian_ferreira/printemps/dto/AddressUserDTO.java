package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressUserDTO extends AddressDTO {
    private boolean isBilled;
}
