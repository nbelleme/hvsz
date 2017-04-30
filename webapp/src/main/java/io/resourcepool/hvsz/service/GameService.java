package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.Game;

import java.util.List;

public interface GameService {

    /**
     * @return All the games.
     */
    List<Game> getAll();

    /**
     * @return all the started games.
     */
    List<Game> getAllRuningGames();

}
