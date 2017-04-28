package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.GameConfig;

public interface  ConfigurationService {

    /**
     * Add and return the game configuration.
     * @param gameConfig GameConfig to add
     * @param gameId id of the game
     * @return GameConfig generated
     */
    GameConfig add(GameConfig gameConfig, Long gameId);

    /**
     * Return the game configuration.
     * @param gameId id of the game
     * @return current GameConfig
     */
    GameConfig get(Long gameId);
}
