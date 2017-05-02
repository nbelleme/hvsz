package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.persistance.models.SupplyZone;

import java.util.ArrayList;

public interface StatusService {

    /**
     * Add and return the game configuration.
     * @param gameStatus GameStatus to add
     * @param gameId     the id of the Game
     * @return GameStatus generated
     */
    GameStatus add(GameStatus gameStatus, Long gameId);

    /**
     * Return the game configuration.
     * @param gameId the id of the Game
     * @return current GameConfig
     */
    GameStatus get(Long gameId);
}
