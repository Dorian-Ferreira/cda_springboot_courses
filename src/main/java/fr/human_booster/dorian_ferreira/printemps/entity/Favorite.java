package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Favorite {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @EmbeddedId
    private FavoriteId id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @MapsId("userUuid")
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @MapsId("lodgingUuid")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Lodging lodging;
}
