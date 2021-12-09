package com.demo.sportradar.controller;

import com.demo.sportradar.dto.Game;
import com.demo.sportradar.dto.Team;
import com.demo.sportradar.service.GameService;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(SportradarController.class)
@AutoConfigureMockMvc
public class SportradarControllerTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Test
    public void whenPostRequestToGameAndValidGame_thenCorrectResponse() throws Exception {
        Game game = new Game(null, new Team("Mexico", 0), new Team("Canada", 5));
        String gameJson = mappingJackson2HttpMessageConverter.getObjectMapper()
                .writeValueAsString(game);

        mockMvc.perform(post("/api/v1/games")
                        .content(gameJson)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenPostRequestToGameAndInvalidGame_thenCorrectResponse() throws Exception {
        Game game = new Game(null, new Team("", 0), new Team("Canada", -5));
        String gameJson = mappingJackson2HttpMessageConverter.getObjectMapper()
                .writeValueAsString(game);

        mockMvc.perform(post("/api/v1/games")
                        .content(gameJson)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.['homeTeam.name']", Is.is("must not be blank")))
                .andExpect(jsonPath("$.['awayTeam.score']", Is.is("must be greater than or equal to 0")));
    }

    @Test
    public void whenGetRequestScoreBoard_thenCorrectResponse() throws Exception {
        List<Game> gameList = List.of(new Game(null, new Team("Mexico", 0), new Team("Canada", 5)));
        given(gameService.getGames()).willReturn(gameList);

        mockMvc.perform(get("/api/v1/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].homeTeam.name", Is.is(gameList.get(0).getHomeTeam().getName())));
    }

}
