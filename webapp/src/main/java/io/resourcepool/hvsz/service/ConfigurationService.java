package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.GameConfig;

public interface  ConfigurationService {

    /**
     * Add and return the game configuration.
     * @param gameConfig GameConfig
     * @param gameId Long
     * @return GameConfig
     */
    GameConfig add(GameConfig gameConfig, Long gameId);

    /**
     * Return the game configuration.
     * @param gameId Long
     * @return GameConfig
     */
    GameConfig get(Long gameId);
}
