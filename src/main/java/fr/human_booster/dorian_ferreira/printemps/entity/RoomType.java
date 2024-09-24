package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsRoomType;
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

    @JsonView(JsonViewsRoomType.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViewsRoomType.TranslationKey.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String translationKey;

    @JsonView(JsonViewsRoomType.Type.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String type;

    @JsonView(JsonViewsRoomType.Lodgings.class)
    @ManyToMany(mappedBy = "roomTypes")
    private List<Lodging> lodgings = new ArrayList<>();
}
