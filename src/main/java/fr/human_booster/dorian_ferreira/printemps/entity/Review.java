package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private float rating;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Lodging lodging;
}
