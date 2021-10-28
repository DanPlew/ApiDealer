package daniel.plewinski.apidealer.chucknorisjokes.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.JokeDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user", password = "qwerty", authorities = {"USER"})
    void getRandomJoke_ShouldReturnRandomJokeFromAnotherApi_WhenUserPostFalseValue() throws Exception {
        // given
        String save = "false";

        // when
        String resultJson = mockMvc.perform(get("/user/getRandomJoke/" + save))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        JokeDTO resultJokeDTO = objectMapper.readValue(resultJson, JokeDTO.class);

        assertAll(
                () -> Assertions.assertThat(resultJokeDTO.getDb_id()).isNull(),
                () -> Assertions.assertThat(resultJokeDTO.getValue()).isNotNull()
        );
    }

    @Test
    @WithMockUser(username = "user", password = "qwerty", authorities = {"USER"})
    void getRandomJoke_ShouldReturnRandomJokeFromAnotherApiAndSaveIt_WhenUserPostTrueValue() throws Exception {
        // given
        String save = "true";
        Long expectingId = 1L;

        // when
        String resultJson = mockMvc.perform(get("/user/getRandomJoke/" + save))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // then
        JokeDTO resultJokeDTO = objectMapper.readValue(resultJson, JokeDTO.class);

        assertAll(
                () -> Assertions.assertThat(resultJokeDTO.getDb_id()).isEqualTo(expectingId),
                () -> Assertions.assertThat(resultJokeDTO.getValue()).isNotNull()
        );
    }

    @Test
    @WithMockUser(username = "admin", password = "qwerty", authorities = {"ADMIN"})
    void getRandomJoke_ShouldReturnForbbidenStatus_WhenAdminPostUsesMethod() throws Exception {
        // given
        String save = "false";

        // when then
        mockMvc.perform(get("/user/getRandomJoke/" + save))
                .andDo(print())
                .andExpect(status().isForbidden());

    }
}