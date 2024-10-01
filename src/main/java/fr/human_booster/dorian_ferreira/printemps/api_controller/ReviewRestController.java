package fr.human_booster.dorian_ferreira.printemps.api_controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.dto.ReviewDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.ReviewLoggedDTO;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class ReviewRestController {

    private ReviewService reviewService;

    @JsonView(JsonViews.ReviewSimpleShow.class)
    @GetMapping(UrlRoute.BASE_REVIEW + "/lodging/{uuid}")
    public CustomResponse getAllForLodging(@PathVariable String uuid) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Review");
        customResponse.setData(reviewService.findAllByLodging(uuid));

        return customResponse;
    }

    @JsonView(JsonViews.ReviewSimpleShow.class)
    @PostMapping(UrlRoute.REVIEW_CREATE)
    public CustomResponse create(@Valid @RequestBody ReviewLoggedDTO dto, Principal principal) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.OK.value());
        customResponse.setEntity("Review");
        customResponse.setData(reviewService.create(dto, principal));

        return customResponse;
    }

    @DeleteMapping(UrlRoute.REVIEW_DELETE + "/{id}")
    public CustomResponse delete(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();

        boolean success = reviewService.delete(id);

        customResponse.setStatus(success ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());
        customResponse.setEntity("Review");
        customResponse.setData(success ? "Deleted" : "Failed");

        return customResponse;
    }

}
