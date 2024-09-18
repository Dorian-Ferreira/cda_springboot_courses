package fr.human_booster.dorian_ferreira.printemps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String roles;

    private boolean isVerified;

    private LocalDate birthAt;
}
