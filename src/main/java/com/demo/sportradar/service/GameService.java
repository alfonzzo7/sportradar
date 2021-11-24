package com.demo.sportradar.service;

import com.demo.sportradar.dto.Game;

import java.util.List;

public interface GameService {

    Game saveGame(Game game);

    List<Game> getGames();

    void deleteGames();

}
