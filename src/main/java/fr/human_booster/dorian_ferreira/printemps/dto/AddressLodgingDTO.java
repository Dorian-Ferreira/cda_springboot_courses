package fr.human_booster.dorian_ferreira.printemps.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressLodgingDTO extends AddressDTO {
    private float longitude;

    private float latitude;
}
