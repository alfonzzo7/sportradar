package com.demo.sportradar.controller;

import com.demo.sportradar.dto.Game;
import com.demo.sportradar.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
public class SportradarController {

    @Autowired
    private GameService gameService;

    /**
     * Save a game in the API
     * @param game
     * @return Game
     */
    @PostMapping(value = "/setGame", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Game setGame(@Validated @RequestBody Game game) {
        gameService.saveGame(game);
        return game;
    }

    /**
     * Get the score board
     * @return List<Game>
     */
    @GetMapping(value = "/getScoreBoard", produces = APPLICATION_JSON_VALUE)
    public List<Game> getScoreBoard() {
        return gameService.getGames();
    }

    /**
     * Delete the score board
     */
    @DeleteMapping(value = "/deleteScoreBoard")
    public void deleteScoreBoard() {
        gameService.deleteGames();
    }

}
