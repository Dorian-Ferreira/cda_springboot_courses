package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.entity.embeddedId.UserLodgingId;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsFavorite;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Favorite {

    @JsonView(JsonViewsFavorite.Id.class)
    @EmbeddedId
    private UserLodgingId id;

    @JsonView(JsonViewsFavorite.CreatedAt.class)
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsFavorite.User.class)
    @NotNull
    @ManyToOne
    @MapsId("userUuid")
    private User user;

    @JsonView(JsonViewsFavorite.Lodging.class)
    @NotNull
    @ManyToOne
    @MapsId("lodgingUuid")
    private Lodging lodging;
}

