package fr.human_booster.dorian_ferreira.printemps.api_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.dto.ReviewDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.RoomTypeDTO;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.ReviewService;
import fr.human_booster.dorian_ferreira.printemps.service.RoomTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RoomTypeRestController {

    private RoomTypeService roomTypeService;

    @JsonView(JsonViews.RoomTypeShow.class)
    @PostMapping(UrlRoute.ROOM_TYPE_CREATE)
    public CustomResponse create(@Valid @RequestBody RoomTypeDTO dto) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Room Type");
        customResponse.setData(roomTypeService.create(dto));

        return customResponse;
    }

    @JsonView(JsonViews.RoomTypeShow.class)
    @GetMapping(UrlRoute.BASE_ROOM_TYPE)
    public CustomResponse list() {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Room Type");
        customResponse.setData(roomTypeService.findAll());

        return customResponse;
    }
}
