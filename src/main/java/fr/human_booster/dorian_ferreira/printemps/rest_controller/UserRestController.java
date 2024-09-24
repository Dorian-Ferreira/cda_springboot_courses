package fr.human_booster.dorian_ferreira.printemps.rest_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @JsonView(JsonViews.UserShow.class)
    @GetMapping(UrlRoute.BASE_USER + "/{uuid}")
    public User getOne(@PathVariable String uuid) {
        return userService.findById(uuid);
    }

}
