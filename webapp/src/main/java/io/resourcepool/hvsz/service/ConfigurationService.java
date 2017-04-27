package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.GameConfig;

public interface  ConfigurationService {

    GameConfig add(GameConfig gameConfig, Long gameId);
    GameConfig get(Long gameId);
}
