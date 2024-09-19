package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressLodgingDTO {
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

    private float longitude;

    private float latitude;

    private String more;
}
