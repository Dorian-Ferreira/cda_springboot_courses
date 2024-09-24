package fr.human_booster.dorian_ferreira.printemps.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.human_booster.dorian_ferreira.printemps.json_views.JsonViewsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomResponse {
    @JsonView(JsonViewsResponse.Response.class)
    private int status;

    @JsonView(JsonViewsResponse.Response.class)
    private String entity;

    @JsonView(JsonViewsResponse.Response.class)
    private Object data;
}
