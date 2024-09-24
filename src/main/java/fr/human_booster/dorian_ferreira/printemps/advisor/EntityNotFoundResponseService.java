package fr.human_booster.dorian_ferreira.printemps.advisor;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.custom_response.CustomResponse;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityNotFoundResponseService {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @JsonView(JsonViewsResponse.Response.class)
    public CustomResponse entityNotFound(EntityNotFoundException exception) {
        CustomResponse customResponse = new CustomResponse();

        customResponse.setStatus(HttpStatus.NOT_FOUND.value());
        customResponse.setEntity(exception.getEntity());
        customResponse.setData("Entity not found");

        return customResponse;
    }
}
