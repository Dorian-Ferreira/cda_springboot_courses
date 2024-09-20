package fr.human_booster.dorian_ferreira.printemps.configuration;

import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Configuration
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private static final int NB_USERS = 200;
    private UserService userService;

    private static final Faker faker = new Faker(Locale.FRANCE);

    @Override
    public void run(String... args) throws Exception {

        createUsers();

    }

    private void createUsers() {
        for (int i = 0; i < NB_USERS; i++) {
            UserCreateDTO userCreateDto = new UserCreateDTO();
            userCreateDto.setPassword("12345");
            userCreateDto.setConfirmPassword("12345");
            userCreateDto.setEmail(faker.internet().emailAddress());

            User user = userService.create(userCreateDto);

            UserUpdateDTO userUpdateDto = new UserUpdateDTO();
            userUpdateDto.setBirthAt(LocalDate.now().minusYears((long)(Math.random()*50)));
            userUpdateDto.setFirstName(faker.name().firstName());
            userUpdateDto.setLastName(faker.name().lastName());
            userUpdateDto.setPhone(faker.phoneNumber().phoneNumber());

            userService.update(userUpdateDto, user.getUuid());
        }
    }
}
