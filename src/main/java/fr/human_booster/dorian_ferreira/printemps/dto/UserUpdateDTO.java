package fr.human_booster.dorian_ferreira.printemps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    private String firstName;

    private String lastName;

    private String password;

    private String confirmPassword;

    private String phone;

    private String photo;

    private LocalDate birthAt;
}
