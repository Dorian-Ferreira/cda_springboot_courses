package fr.human_booster.dorian_ferreira.printemps.api_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.FavoriteId;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.FavoriteService;
import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRestController {

    private UserService userService;
    private FavoriteService favoriteService;

    @JsonView(JsonViews.UserShow.class)
    @GetMapping(UrlRoute.BASE_USER + "/{uuid}")
    public CustomResponse getOne(@PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(userService.findById(uuid));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @GetMapping(UrlRoute.USER_CREATE)
    public CustomResponse create(@RequestBody UserCreateDTO dto) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.CREATED.value());
        customResponse.setEntity("User");
        customResponse.setData(userService.create(dto));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @GetMapping(UrlRoute.USER_EDIT + "/{uuid}")
    public CustomResponse update(@RequestBody UserUpdateDTO dto, @PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(userService.update(dto, uuid));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @GetMapping(UrlRoute.USER_FAVORITE)
    public CustomResponse addFavorite(@RequestBody FavoriteId dto) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(favoriteService.persist(dto));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @GetMapping(UrlRoute.USER_DELETE + "/{uuid}")
    public CustomResponse delete(@PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        boolean success = userService.delete(uuid);

        customResponse.setStatus(success ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());
        customResponse.setEntity("User");
        customResponse.setData(success ? "Deleted" : "Failed");

        return customResponse;
    }

}
