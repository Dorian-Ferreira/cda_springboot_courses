package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsReview;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Review {

    @JsonView(JsonViewsReview.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViewsReview.Content.class)
    @NotNull
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @JsonView(JsonViewsReview.Rating.class)
    @Column(nullable = false)
    private float rating;

    @JsonView(JsonViewsReview.CreatedAt.class)
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsReview.User.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @JsonView(JsonViewsReview.Lodging.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Lodging lodging;
}
