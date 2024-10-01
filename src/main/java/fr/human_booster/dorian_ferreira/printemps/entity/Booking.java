package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsBooking;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsLodging;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
public class Booking {

    @JsonView(JsonViewsBooking.Uuid.class)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @JsonView(JsonViewsBooking.Number.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String number;

    @JsonView(JsonViewsBooking.CreatedAt.class)
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsBooking.StartedAt.class)
    @NotNull
    @Column(nullable = false)
    private LocalDateTime startedAt;

    @JsonView(JsonViewsBooking.FinishedAt.class)
    @NotNull
    @Column(nullable = false)
    private LocalDateTime finishedAt;

    @JsonView(JsonViewsBooking.Quantity.class)
    @Min(1)
    @Column(nullable = false)
    private int quantity;

    @JsonView(JsonViewsBooking.Canceled.class)
    @Column(nullable = false)
    private Boolean isCanceled;

    @JsonView(JsonViewsBooking.Lodging.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Lodging lodging;

    @JsonView(JsonViewsBooking.User.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}
