package fr.human_booster.dorian_ferreira.printemps.configuration;

import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

    }
}
