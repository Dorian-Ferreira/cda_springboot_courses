package fr.human_booster.dorian_ferreira.printemps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String street;

    private String number;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String zipCode;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String city;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String country;

    private float longitude;

    private float latitude;

    private String more;

    @Column(nullable = false)
    private boolean isBilled;

    @ManyToOne
    private User user;
}
