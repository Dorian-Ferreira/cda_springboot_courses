package fr.human_booster.dorian_ferreira.printemps.api_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.dto.AddressUserDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.embeddedId.UserLodgingId;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.AddressService;
import fr.human_booster.dorian_ferreira.printemps.service.FavoriteService;
import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class UserRestController {

    private UserService userService;
    private AddressService addressService;
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
    @GetMapping(UrlRoute.USER_ACTIVATION + "/{activationCode}")
    public CustomResponse activate(@PathVariable String activationCode) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(userService.activate(activationCode));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @PutMapping(UrlRoute.USER_EDIT)
    public CustomResponse update(@Valid @RequestBody UserUpdateDTO dto, Principal principal) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(userService.update(dto, principal));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @PostMapping(UrlRoute.USER_EDIT + "/address")
    public CustomResponse addAddress(@Valid @RequestBody AddressUserDTO dto, Principal principal) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(addressService.create(dto, principal));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @DeleteMapping(UrlRoute.USER_DELETE + "/address/{id}")
    public CustomResponse removeAddress(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(addressService.delete(id));

        return customResponse;
    }

    @JsonView(JsonViews.UserShow.class)
    @PostMapping(UrlRoute.USER_FAVORITE)
    public CustomResponse handleFavorite(@Valid @RequestBody UserLodgingId dto) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("User");
        customResponse.setData(favoriteService.persist(dto));

        return customResponse;
    }

    @DeleteMapping(UrlRoute.USER_DELETE + "/{uuid}")
    public CustomResponse delete(@PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        boolean success = userService.delete(uuid);

        customResponse.setStatus(success ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());
        customResponse.setEntity("User");
        customResponse.setData(success ? "Deleted" : "Failed");

        return customResponse;
    }
}