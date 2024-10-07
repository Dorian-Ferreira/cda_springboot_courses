package fr.human_booster.dorian_ferreira.printemps.api_controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginInJsonFromDate("lisa.mathieu@yahoo.fr", "12345")));

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void testLoginFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginInJsonFromDate("lisa.mathieu@yahoo.fr", "123456")));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoginValidationPasswordFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginInJsonFromDate("lisa.mathieu@yahoo.fr", "")));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoginValidationUsernameFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginInJsonFromDate("", "12345")));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterInJsonFromDate(
                        "oui.test@yahoo.fr",
                        LocalDate.now().minusYears(12),
                        "Oui",
                        "Test",
                        "12345",
                        "12345"
                )));

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.entity").exists())
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    public void testRegisterFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterInJsonFromDate(
                        "noa.michel@hotmail.fr",
                        LocalDate.now().minusYears(12),
                        "Oui",
                        "Test",
                        "12345",
                        "12345"
                )));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testRegisterValidationMailFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterInJsonFromDate(
                        "",
                        LocalDate.now().minusYears(12),
                        "Oui",
                        "Test",
                        "12345",
                        "12345"
                )));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testRegisterValidationBirthDateFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterInJsonFromDate(
                        "noa.michel@hotmail.fr",
                        LocalDate.now().plusYears(12),
                        "Oui",
                        "Test",
                        "12345",
                        "12345"
                )));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testRegisterValidationPasswordFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(UrlRoute.REGISTER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterInJsonFromDate(
                        "noa.michel@hotmail.fr",
                        LocalDate.now().minusYears(12),
                        "Oui",
                        "Test",
                        "",
                        "12345"
                )));

        resultActions
                .andExpect(status().is4xxClientError());
    }

    private String getLoginInJsonFromDate(String email, String pwd) {
        return "{" +
                "  \"email\": \"" + email + "\"," +
                "  \"password\": \"" + pwd + "\"" +
                "}";
    }

    private String getRegisterInJsonFromDate(String email, LocalDate birthDate, String firstName, String lastName, String password, String confirmPassword) {
        return "{\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"birthAt\": \"" + birthDate + "\",\n" +
                "  \"firstName\": \" " + firstName + "\",\n" +
                "  \"lastName\": \"" + lastName + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"confirmPassword\": \"" + confirmPassword + "\"\n" +
                "}";
    }
}
