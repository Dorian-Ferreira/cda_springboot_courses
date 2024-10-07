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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "arthur.laine@yahoo.fr", roles = "USER")
    public void testCreateSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.BOOKING_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCreateInJsonFromDate(
                        LocalDate.now().minusYears(12).atStartOfDay(),
                        LocalDate.now().minusYears(12).plusDays(12).atStartOfDay(),
                        6,
                        "26749a10-5590-455c-84b1-94889def61da"
                )));

        resultActions
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testCreateAnonymousFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.BOOKING_CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCreateInJsonFromDate(
                        LocalDate.now().minusYears(12).atStartOfDay(),
                        LocalDate.now().minusYears(12).plusDays(12).atStartOfDay(),
                        6,
                        "26749a10-5590-455c-84b1-94889def61da"
                )));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    private String getCreateInJsonFromDate(LocalDateTime startedAt, LocalDateTime finishedAt, int quantity, String lodging) {
        return "{\n" +
                "  \"startedAt\": \"" + startedAt + "\",\n" +
                "  \"finishedAt\": \"" + finishedAt + "\",\n" +
                "  \"quantity\": " + quantity + ",\n" +
                "  \"lodgingUuid\": \"" + lodging + "\"\n" +
                "}";
    }
}
