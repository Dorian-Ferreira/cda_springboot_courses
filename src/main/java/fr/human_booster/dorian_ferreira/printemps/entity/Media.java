package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String path;

    @NotNull
    @NotBlank
    @Column(nullable = false, length = 12)
    private String extension;

    @NotNull
    @ManyToOne
    private Lodging lodging;
}
