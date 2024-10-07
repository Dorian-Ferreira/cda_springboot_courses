package fr.human_booster.dorian_ferreira.printemps.api_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.custom_response.JwtResponse;
import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserLoginDTO;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.security.JwtAuthenticatorService;
import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecurityRestController {

    private UserService userService;
    private JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping(UrlRoute.LOGIN)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody UserLoginDTO dto) {
        return jwtAuthenticatorService.authenticate(dto);
    }

    @JsonView(JsonViews.UserShow.class)
    @PostMapping(UrlRoute.REGISTER)
    public CustomResponse register(@Valid @RequestBody UserCreateDTO dto) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.CREATED.value());
        customResponse.setEntity("User");
        customResponse.setData(userService.create(dto));

        return customResponse;
    }
}
