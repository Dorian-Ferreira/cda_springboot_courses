package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class FavoriteId implements Serializable {

    private String userUuid;
    private String lodgingUuid;
}
