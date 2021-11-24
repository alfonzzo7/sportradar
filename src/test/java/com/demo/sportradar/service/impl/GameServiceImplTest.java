package com.demo.sportradar.service.impl;

import com.demo.sportradar.dto.Game;
import com.demo.sportradar.entity.GameEntity;
import com.demo.sportradar.repository.GameRepository;
import com.demo.sportradar.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GameServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public GameService gameService() {
            return new GameServiceImpl();
        }
    }

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    private static final String ARGENTINA = "Argentina";
    private static final String AUSTRALIA = "Australia";
    private static final String BRAZIL    = "Brazil";
    private static final String CANADA    = "Canada";
    private static final String FRANCE    = "France";
    private static final String GERMANY   = "Germany";
    private static final String ITALY     = "Italy";
    private static final String MEXICO    = "Mexico";
    private static final String SPAIN     = "Spain";
    private static final String URUGUAY   = "Uruguay";

    private Game saveGame(Game game) {
        GameEntity gameEntity = new GameEntity(null, game.getHomeTeamName(), game.getHomeTeamScore(), game.getAwayTeamName(), game.getAwayTeamScore(), game.getHomeTeamScore() + game.getAwayTeamScore());
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
        when(objectMapper.convertValue(any(Game.class), eq(GameEntity.class))).thenReturn(gameEntity);
        when(objectMapper.convertValue(any(GameEntity.class), eq(Game.class))).thenReturn(game);

        return gameService.saveGame(game);
    }

    @Test
    public void whenSaveGame() {
        Game game = new Game("Mexico", 0, "Canada", 5);
        Game gameSaved = saveGame(game);
        assertEquals(gameSaved, game);
    }

    @Test
    public void whenGetGames(){
        List<GameEntity> gameEntityList = List.of(
                new GameEntity(1L, MEXICO, 0, CANADA, 5, 5),
                new GameEntity(2L, SPAIN, 10, BRAZIL, 2, 12),
                new GameEntity(3L, GERMANY, 2, FRANCE, 2, 4),
                new GameEntity(4L, URUGUAY, 6, ITALY, 6, 12),
                new GameEntity(5L, ARGENTINA, 3, AUSTRALIA, 1, 4)
        );

        when(gameRepository.findAll()).thenReturn(gameEntityList);
        List<Game> gameList = gameService.getGames();
        assertEquals(gameList.get(0).getHomeTeamName(), URUGUAY);
        assertEquals(gameList.get(0).getAwayTeamName(), ITALY);

        assertEquals(gameList.get(1).getHomeTeamName(), SPAIN);
        assertEquals(gameList.get(1).getAwayTeamName(), BRAZIL);

        assertEquals(gameList.get(2).getHomeTeamName(), MEXICO);
        assertEquals(gameList.get(2).getAwayTeamName(), CANADA);

        assertEquals(gameList.get(3).getHomeTeamName(), ARGENTINA);
        assertEquals(gameList.get(3).getAwayTeamName(), AUSTRALIA);

        assertEquals(gameList.get(4).getHomeTeamName(), GERMANY);
        assertEquals(gameList.get(4).getAwayTeamName(), FRANCE);
    }
}
