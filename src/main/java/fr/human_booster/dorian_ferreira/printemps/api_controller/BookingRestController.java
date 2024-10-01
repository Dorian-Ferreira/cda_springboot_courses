package fr.human_booster.dorian_ferreira.printemps.api_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.dto.BookingDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.BookingLoggedDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.BookingService;
import fr.human_booster.dorian_ferreira.printemps.service.LodgingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class BookingRestController {

    private BookingService bookingService;

    @JsonView(JsonViews.BookingShow.class)
    @GetMapping(UrlRoute.BASE_BOOKING + "/lodging/{uuid}")
    public CustomResponse getAllForLodging(@PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Booking");
        customResponse.setData(bookingService.findAllByLodging(uuid));

        return customResponse;
    }

    @JsonView(JsonViews.BookingShow.class)
    @GetMapping(UrlRoute.BASE_BOOKING + "/{id}")
    public CustomResponse getOne(@PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Booking");
        customResponse.setData(bookingService.findById(id));

        return customResponse;
    }

    @JsonView(JsonViews.BookingShow.class)
    @PostMapping(UrlRoute.BOOKING_CREATE)
    public CustomResponse create(@Valid @RequestBody BookingLoggedDTO dto, Principal principal) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Booking");
        customResponse.setData(bookingService.create(dto, principal));

        return customResponse;
    }

    @DeleteMapping(UrlRoute.BOOKING_CANCEL + "/{id}")
    public CustomResponse cancel(@PathVariable String id) {
        CustomResponse customResponse = new CustomResponse();

        boolean success = bookingService.delete(id);

        customResponse.setStatus(success ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());
        customResponse.setEntity("Booking");
        customResponse.setData(success ? "Deleted" : "Failed");

        return customResponse;
    }

}
