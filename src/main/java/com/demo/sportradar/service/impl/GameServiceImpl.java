package com.demo.sportradar.service.impl;

import com.demo.sportradar.dto.Game;
import com.demo.sportradar.entity.GameEntity;
import com.demo.sportradar.repository.GameRepository;
import com.demo.sportradar.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GameRepository gameRepository;

    /**
     * Insert a game in the DB
     * @param game
     * @return Game
     */
    @Override
    public Game saveGame(Game game) {
        GameEntity gameEntity = mapper.convertValue(game, GameEntity.class);
        gameEntity.setTotalScore(gameEntity.getHomeTeamScore() + gameEntity.getAwayTeamScore());
        return mapper.convertValue(gameRepository.save(gameEntity), Game.class);
    }

    /**
     * Return the list of games sorted by total score and last added game
     * @return List<Game>
     */
    @Override
    public List<Game> getGames() {
        List<GameEntity> gameEntityList = gameRepository.findAll();
        Comparator<GameEntity> multipleComparator = Comparator.comparing(GameEntity::getTotalScore).thenComparing(Comparator.comparing(GameEntity::getId));
        List<Game> gameList = gameEntityList.stream().sorted(multipleComparator.reversed()).map(this::convertGameEntityToGame).collect(Collectors.toList());
        return gameList;
    }

    /**
     * Delete all games
     */
    @Override
    public void deleteGames() {
        gameRepository.deleteAll();
    }

    private Game convertGameEntityToGame(GameEntity gameEntity) {
        return new Game(gameEntity.getHomeTeamName(), gameEntity.getHomeTeamScore(), gameEntity.getAwayTeamName(), gameEntity.getAwayTeamScore());
    }
}
