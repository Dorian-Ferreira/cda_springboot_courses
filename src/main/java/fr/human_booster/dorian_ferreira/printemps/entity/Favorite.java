package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Favorite {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @EmbeddedId
    private FavoriteId id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne
    @MapsId("userUuid")
    private User user;

    @NotNull
    @ManyToOne
    @MapsId("lodgingUuid")
    private Lodging lodging;
}

