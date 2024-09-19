package fr.human_booster.dorian_ferreira.printemps.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LodgingDTO {
    @NotNull
    @NotBlank
    private String name;

    private String description;

    @Min(1)
    private int capacity;

    private boolean isAccessible;

    @Min(1)
    private int nightPrice;

    private Long addressId;

    private AddressLodgingDTO addressDTO;
}
