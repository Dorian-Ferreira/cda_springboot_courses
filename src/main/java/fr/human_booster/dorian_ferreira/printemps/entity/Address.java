package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @JsonView(JsonViewsAddress.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViewsAddress.Street.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String street;

    @JsonView(JsonViewsAddress.Number.class)
    private String number;

    @JsonView(JsonViewsAddress.ZipCode.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String zipCode;

    @JsonView(JsonViewsAddress.City.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String city;

    @JsonView(JsonViewsAddress.Country.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String country;

    @JsonView(JsonViewsAddress.Longitude.class)
    private float longitude;

    @JsonView(JsonViewsAddress.Latitude.class)
    private float latitude;

    @JsonView(JsonViewsAddress.More.class)
    private String more;

    @JsonView(JsonViewsAddress.Billed.class)
    private Boolean isBilled;

    @JsonView(JsonViewsAddress.User.class)
    @ManyToOne
    private User user;
}
