package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String translationKey;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String type;

    @ManyToMany(mappedBy = "roomTypes")
    private List<Lodging> lodgings = new ArrayList<>();
}
