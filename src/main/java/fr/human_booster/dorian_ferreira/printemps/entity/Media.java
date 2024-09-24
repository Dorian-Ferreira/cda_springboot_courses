package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsMedia;
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

    @JsonView(JsonViewsMedia.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViewsMedia.Path.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String path;

    @JsonView(JsonViewsMedia.Extension.class)
    @NotNull
    @NotBlank
    @Column(nullable = false, length = 12)
    private String extension;

    @JsonView(JsonViewsMedia.Lodging.class)
    @NotNull
    @ManyToOne
    private Lodging lodging;
}
