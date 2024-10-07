package fr.human_booster.dorian_ferreira.printemps.api_controller;

import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LodgingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "carla.dubois@gmail.com", roles = "ADMIN")
    public void testCreateSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.LODGING_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCreateInJsonFromDate("Super","oui",12,true, 12, "12", "12", "12", "12", "12", "", 12f, 12f
                )));

        resultActions
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testCreateAnonymousFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.LODGING_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCreateInJsonFromDate("Super","oui",12,true, 12, "12", "12", "12", "12", "12", "", 12f, 12f
                )));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    private String getCreateInJsonFromDate(String name,
                                           String description,
                                           int capacity,
                                           boolean isAccessible,
                                           int nightPrice,
                                           String street,
                                           String number,
                                           String zipCode,
                                           String city,
                                           String country,
                                           String more,
                                           float longitude,
                                           float latitude
    ) {
        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"description\": \"" + description + "\",\n" +
                "  \"capacity\": " + capacity + ",\n" +
                "  \"isAccessible\": " + isAccessible + ",\n" +
                "  \"nightPrice\": " + nightPrice + ",\n" +
                "  \"addressDTO\": {\n" +
                "    \"street\": \"" + street + "\",\n" +
                "    \"number\": \"" + number + "\",\n" +
                "    \"zipCode\": \"" + zipCode + "\",\n" +
                "    \"city\": \"" + city + "\",\n" +
                "    \"country\": \"" + country + "\",\n" +
                "    \"more\": \"" + more + "\",\n" +
                "    \"longitude\": " + longitude + ",\n" +
                "    \"latitude\": " + latitude + "\n" +
                "  }\n" +
                "}";
    }
}
