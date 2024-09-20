package fr.human_booster.dorian_ferreira.printemps.entity;

import fr.human_booster.dorian_ferreira.printemps.slugger.SluggerInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.io.Serializable;
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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(1)
    @Column(nullable = false)
    private int capacity;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private boolean isAccessible;

    @Min(1)
    @Column(nullable = false)
    private int nightPrice;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String slug;

    @OneToMany(mappedBy = "lodging")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "lodging")
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "lodging")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "lodging")
    private List<Media> medias = new ArrayList<>();

    @ManyToMany
    private List<Room> rooms = new ArrayList<>();

    @NotNull
    @OneToOne
    private Address address;

    @Override
    public String getField() {
        return name;
    }
}
