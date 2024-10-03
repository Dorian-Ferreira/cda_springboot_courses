package fr.human_booster.dorian_ferreira.printemps.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsReview;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements UserDetails {

    @JsonView(JsonViewsUser.Uuid.class)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @JsonView(JsonViewsUser.FirstName.class)
    private String firstName;

    @JsonView(JsonViewsUser.LastName.class)
    private String lastName;

    @JsonView(JsonViewsUser.Email.class)
    @Email
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @JsonView(JsonViewsUser.Password.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @JsonView(JsonViewsUser.Roles.class)
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String roles;

    @JsonView(JsonViewsUser.Verified.class)
    @Column(nullable = false)
    private boolean verified;

    @JsonView(JsonViewsUser.BirthAt.class)
    private LocalDate birthAt;

    @JsonView(JsonViewsUser.CreatedAt.class)
    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsUser.DeletedAt.class)
    private LocalDateTime deletedAt;

    @JsonView(JsonViewsUser.Phone.class)
    private String phone;

    @JsonView(JsonViewsUser.Photo.class)
    private String photo;

    @JsonView(JsonViewsUser.ActivationCode.class)
    private String activationCode;

    @JsonView(JsonViewsUser.ActivationTimeout.class)
    private LocalDateTime activationTimeout;

    @JsonView(JsonViewsUser.Bookings.class)
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    @JsonView(JsonViewsUser.Addresses.class)
    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

    @JsonView(JsonViewsUser.Reviews.class)
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @JsonView(JsonViewsUser.Favorites.class)
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", isVerified=" + verified +
                ", birthAt=" + birthAt +
                ", createdAt=" + createdAt +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    @JsonView(JsonViewsUser.IsAdmin.class)
    public boolean isAdmin() {
        return roles.contains("ROLE_ADMIN");
    }

    public boolean isActive() {
        return activationCode == null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }
}
