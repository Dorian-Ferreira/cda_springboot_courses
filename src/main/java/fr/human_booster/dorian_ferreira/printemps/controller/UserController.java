package fr.human_booster.dorian_ferreira.printemps.controller;

import fr.human_booster.dorian_ferreira.printemps.dto.FavoriteDto;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Favorite;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.service.FavoriteService;
import fr.human_booster.dorian_ferreira.printemps.service.LodgingService;
import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final LodgingService lodgingService;
    private final FavoriteService favoriteService;

    @GetMapping(path = "api/users")
    List<User> listUser() {
        return userService.getAll();
    }

    @GetMapping(path = "api/lodgings")
    List<Lodging> listLodging() {
        return lodgingService.getAll();
    }

    @PostMapping(path = "api/user")
    User createUser(@RequestBody UserCreateDTO userDTO) {
        return userService.create(userDTO);
    }

    @PostMapping(path = "api/lodgings")
    Lodging createLodging(@RequestBody LodgingCreateDTO lodgingCreateDTO) {
        return lodgingService.create(lodgingCreateDTO);
    }

    @PostMapping(path = "api/favorite")
    Favorite createFavorite(@RequestBody FavoriteDto favoriteDto) {
        return favoriteService.create(favoriteDto);
    }
}
