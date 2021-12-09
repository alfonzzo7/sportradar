package com.demo.sportradar.service.impl;

import com.demo.sportradar.dto.Game;
import com.demo.sportradar.dto.Team;
import com.demo.sportradar.entity.GameEntity;
import com.demo.sportradar.entity.TeamEntity;
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
        GameEntity gameEntity = new GameEntity(null,
                new TeamEntity(null, game.getHomeTeam().getName(), game.getHomeTeam().getScore()),
                new TeamEntity(null, game.getAwayTeam().getName(), game.getAwayTeam().getScore()),
                game.getHomeTeam().getScore() + game.getAwayTeam().getScore());
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
        when(objectMapper.convertValue(any(Game.class), eq(GameEntity.class))).thenReturn(gameEntity);
        when(objectMapper.convertValue(any(GameEntity.class), eq(Game.class))).thenReturn(game);

        return gameService.saveGame(game);
    }

    @Test
    public void whenSaveGame() {
        Game game = new Game(1L, new Team("Mexico", 0), new Team("Canada", 5));
        Game gameSaved = saveGame(game);
        assertEquals(gameSaved, game);
    }

    @Test
    public void whenGetGames(){
        List<GameEntity> gameEntityList = List.of(
                new GameEntity(1L, new TeamEntity(null, MEXICO, 0), new TeamEntity(null, CANADA, 5), 5),
                new GameEntity(2L, new TeamEntity(null, SPAIN, 10), new TeamEntity(null, BRAZIL, 2), 12),
                new GameEntity(3L, new TeamEntity(null, GERMANY, 2), new TeamEntity(null, FRANCE, 2), 4),
                new GameEntity(4L, new TeamEntity(null, URUGUAY, 6), new TeamEntity(null, ITALY, 6), 12),
                new GameEntity(5L, new TeamEntity(null, ARGENTINA, 3), new TeamEntity(null, AUSTRALIA, 1), 4)
        );

        when(gameRepository.findAll()).thenReturn(gameEntityList);
        List<Game> gameList = gameService.getGames();
        assertEquals(gameList.get(0).getHomeTeam().getName(), URUGUAY);
        assertEquals(gameList.get(0).getAwayTeam().getName(), ITALY);

        assertEquals(gameList.get(1).getHomeTeam().getName(), SPAIN);
        assertEquals(gameList.get(1).getAwayTeam().getName(), BRAZIL);

        assertEquals(gameList.get(2).getHomeTeam().getName(), MEXICO);
        assertEquals(gameList.get(2).getAwayTeam().getName(), CANADA);

        assertEquals(gameList.get(3).getHomeTeam().getName(), ARGENTINA);
        assertEquals(gameList.get(3).getAwayTeam().getName(), AUSTRALIA);

        assertEquals(gameList.get(4).getHomeTeam().getName(), GERMANY);
        assertEquals(gameList.get(4).getAwayTeam().getName(), FRANCE);
    }
}
