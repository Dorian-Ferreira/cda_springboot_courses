package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressUserDTO {
    @NotNull
    @NotBlank
    private String street;

    private String number;

    @NotNull
    @NotBlank
    private String zipCode;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String country;

    private String more;

    private boolean isBilled;
}
