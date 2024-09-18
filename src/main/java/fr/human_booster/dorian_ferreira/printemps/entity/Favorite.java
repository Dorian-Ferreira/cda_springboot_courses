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
@IdClass(FavoriteId.class)
public class Favorite {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @EmbeddedId
//    private FavoriteId id;

    @Id
    private String userUuid;

    @Id
    private String lodgingUuid;

    @Column(nullable = false)
    private LocalDateTime createdAt;

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn( nullable = false)
//    private Lodging lodging;
}
