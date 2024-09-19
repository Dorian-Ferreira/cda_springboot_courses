package fr.human_booster.dorian_ferreira.printemps.entity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String number;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime startedAt;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime finishedAt;

    @Min(1)
    @Column(nullable = false)
    private int quantity;

    @NotNull
    @NotBlank
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @NotNull
    @NotBlank
    @ManyToOne
    @JoinColumn(nullable = false)
    private Lodging lodging;
}
