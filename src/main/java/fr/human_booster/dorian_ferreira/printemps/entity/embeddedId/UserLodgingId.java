package fr.human_booster.dorian_ferreira.printemps.entity.embeddedId;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserLodgingId implements Serializable {

    private String lodgingUuid;
    private String userUuid;
}
