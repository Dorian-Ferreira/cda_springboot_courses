package fr.human_booster.dorian_ferreira.printemps.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomResponse {
    @JsonView(JsonViews.CustomResponse.class)
    private int status;

    @JsonView(JsonViews.CustomResponse.class)
    private String entity;

    @JsonView(JsonViews.CustomResponse.class)
    private Object data;
}
