package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
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
public class Lodging {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private boolean isAccessible;

    @Column(nullable = false)
    private int nightPrice;

    @Column(columnDefinition = "TEXT")
    private String description;

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

    @OneToOne
    private Address address;
}
