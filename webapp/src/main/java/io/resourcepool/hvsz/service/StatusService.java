package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.GameStatus;

public interface StatusService {

    /**
     * Add and return the game configuration.
     * @param gameStatus GameStatus
     * @param gameId Long
     * @return GameStatus
     */
    GameStatus add(GameStatus gameStatus, Long gameId);

    /**
     * Return the game configuration.
     * @param gameId Long
     * @return GameConfig
     */
    GameStatus get(Long gameId);
}
