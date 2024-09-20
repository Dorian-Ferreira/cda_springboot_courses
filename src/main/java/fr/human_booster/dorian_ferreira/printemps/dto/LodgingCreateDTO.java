package fr.human_booster.dorian_ferreira.printemps.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgingCreateDTO extends LodgingUpdateDTO {
    private Long addressId;

    private AddressLodgingDTO addressDTO;
}
