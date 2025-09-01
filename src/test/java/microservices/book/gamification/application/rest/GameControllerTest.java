package microservices.book.gamification.application.rest;

import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.application.port.input.IGameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(GameController.class)
class GameControllerTest {
    @MockitoBean
    private IGameService gameService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ChallengeSolvedDto> json;

    @Test
    public void postResultTest() throws Exception {
        // given
        ChallengeSolvedDto dto = new ChallengeSolvedDto(1L,
                true, 10, 40, 1L, "john");
        given(gameService.newAttemptForUser(dto))
                .willReturn(new microservices.book.gamification.domain.model.GameResult(10,
                        List.of(microservices.book.gamification.domain.model.BadgeType.FIRST_WON)));

        // when
        var response = mvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                        .post("/attempts")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(json.write(dto).getJson())
        ).andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(200);
        then(response.getContentAsString()).isEqualTo("");
    }
}