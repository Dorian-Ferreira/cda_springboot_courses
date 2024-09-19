package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Email
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String roles;

    @Column(nullable = false)
    private boolean isVerified;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthAt;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String phone;

    private String photo;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites = new ArrayList<>();
}
