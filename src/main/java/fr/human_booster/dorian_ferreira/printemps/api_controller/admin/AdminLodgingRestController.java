package fr.human_booster.dorian_ferreira.printemps.api_controller.admin;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.LodgingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AdminLodgingRestController {

    private LodgingService lodgingService;
    @JsonView(JsonViews.LodgingShow.class)
    @PostMapping(UrlRoute.LODGING_CREATE)
    public CustomResponse create(@Valid @RequestBody LodgingCreateDTO dto) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.create(dto));

        return customResponse;
    }

    @JsonView(JsonViews.LodgingShow.class)
    @GetMapping(UrlRoute.LODGING_EDIT + "/{uuid}/add/{id}")
    public CustomResponse addRoom(@PathVariable String uuid, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.handleRoomType(uuid, id));

        return customResponse;
    }

    @JsonView(JsonViews.LodgingShow.class)
    @DeleteMapping(UrlRoute.LODGING_EDIT + "/{uuid}/delete/{id}")
    public CustomResponse removeRoom(@PathVariable String uuid, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.handleRoomType(uuid, id));

        return customResponse;
    }

    @JsonView(JsonViews.LodgingShow.class)
    @PutMapping(UrlRoute.LODGING_EDIT + "/{uuid}")
    public CustomResponse update(@Valid @RequestBody LodgingUpdateDTO dto, @PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.update(dto, uuid));

        return customResponse;
    }

    @DeleteMapping(UrlRoute.LODGING_DELETE + "/{uuid}")
    public CustomResponse delete(@PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        boolean success = lodgingService.delete(uuid);

        customResponse.setStatus(success ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(success ? "Deleted" : "Failed");

        return customResponse;
    }
}
