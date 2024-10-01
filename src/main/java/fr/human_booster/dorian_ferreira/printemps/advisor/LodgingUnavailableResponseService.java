package fr.human_booster.dorian_ferreira.printemps.advisor;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.exception.AlreadyActiveException;
import fr.human_booster.dorian_ferreira.printemps.exception.LodgingUnavailableException;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LodgingUnavailableResponseService {

    @ResponseBody
    @ExceptionHandler(LodgingUnavailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @JsonView(JsonViews.CustomResponse.class)
    public CustomResponse entityNotFound(LodgingUnavailableException exception) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customResponse.setEntity("Booking");
        customResponse.setData("Lodging unavailable.");

        return customResponse;
    }
}
