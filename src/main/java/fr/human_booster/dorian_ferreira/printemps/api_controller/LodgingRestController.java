package fr.human_booster.dorian_ferreira.printemps.api_controller;

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

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class LodgingRestController {

    private LodgingService lodgingService;

    @JsonView(JsonViews.LodgingList.class)
    @GetMapping(UrlRoute.BASE_LODGING)
    public CustomResponse getAll() {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.findAllOpen());

        return customResponse;
    }

    @JsonView(JsonViews.LodgingList.class)
    @GetMapping(UrlRoute.LODGING_SEARCH + "/{name}")
    public CustomResponse searchByName(@PathVariable String name) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.findAllOpenByName(name));

        return customResponse;
    }

    @JsonView(JsonViews.LodgingList.class)
    @GetMapping(UrlRoute.LODGING_SEARCH)
    public CustomResponse searchAll(
            @RequestParam(value="startDate", required = false) LocalDate startDate,
            @RequestParam(value="endDate", required = false) LocalDate endDate,
            @RequestParam(value="nightPrice", required = false) Integer nightPrice,
            @RequestParam(value="capacity", required = false) Integer capacity,
            @RequestParam(value="isAccessible", required = false) Boolean isAccessible
    ) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.searchAllOpen(startDate, endDate, nightPrice, capacity, isAccessible));

        return customResponse;
    }

    @JsonView(JsonViews.LodgingShow.class)
    @GetMapping(UrlRoute.BASE_LODGING + "/{slug}")
    public CustomResponse getOne(@PathVariable String slug) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Lodging");
        customResponse.setData(lodgingService.findBySlug(slug));

        return customResponse;
    }

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
        customResponse.setData(lodgingService.addRoomType(uuid, id));

        return customResponse;
    }

    @JsonView(JsonViews.LodgingShow.class)
    @PostMapping(UrlRoute.LODGING_EDIT + "/{uuid}")
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
