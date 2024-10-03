package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsLodging;
import fr.human_booster.dorian_ferreira.printemps.slugger.SluggerInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(
    indexes = {
        @Index(columnList = "slug")
    }
)
public class Lodging implements SluggerInterface {

    @JsonView(JsonViewsLodging.Uuid.class)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @JsonView(JsonViewsLodging.Name.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String name;

    @JsonView(JsonViewsLodging.Capacity.class)
    @Min(1)
    @Column(nullable = false)
    private int capacity;

    @JsonView(JsonViewsLodging.Accessible.class)
    @Column(nullable = false)
    private Boolean isAccessible;

    @JsonView(JsonViewsLodging.Open.class)
    @Column(nullable = false)
    private Boolean isOpen;

    @JsonView(JsonViewsLodging.NightPrice.class)
    @Min(1)
    @Column(nullable = false)
    private int nightPrice;

    @JsonView(JsonViewsLodging.Description.class)
    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonView(JsonViewsLodging.Slug.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String slug;

    @JsonView(JsonViewsLodging.Address.class)
    @NotNull
    @OneToOne
    private Address address;

    @JsonView(JsonViewsLodging.Reviews.class)
    @OneToMany(mappedBy = "lodging")
    private List<Review> reviews = new ArrayList<>();

    @JsonView(JsonViewsLodging.Favorites.class)
    @OneToMany(mappedBy = "lodging")
    private List<Favorite> favorites = new ArrayList<>();

    @JsonView(JsonViewsLodging.Bookings.class)
    @OneToMany(mappedBy = "lodging")
    private List<Booking> bookings = new ArrayList<>();

    @JsonView(JsonViewsLodging.Medias.class)
    @OneToMany(mappedBy = "lodging")
    private List<Media> medias = new ArrayList<>();

    @JsonView(JsonViewsLodging.RoomTypes.class)
    @ManyToMany
    private List<RoomType> roomTypes = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }

    public void handleRoom(RoomType roomType) {
        if(roomTypes.contains(roomType)) {
            roomTypes.remove(roomType);
        } else {
            roomTypes.add(roomType);
        }
    }
}
