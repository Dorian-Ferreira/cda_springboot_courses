package fr.human_booster.dorian_ferreira.printemps.advisor;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.exception.AlreadyActiveException;
import fr.human_booster.dorian_ferreira.printemps.exception.ExpiredCodeException;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlreadyActiveResponseService {

    @ResponseBody
    @ExceptionHandler(AlreadyActiveException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @JsonView(JsonViews.CustomResponse.class)
    public CustomResponse entityNotFound(AlreadyActiveException exception) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customResponse.setEntity("User");
        customResponse.setData("Activation code not found.");

        return customResponse;
    }
}
