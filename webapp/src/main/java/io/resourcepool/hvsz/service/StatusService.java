package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.GameStatus;

public interface StatusService {
    GameStatus add(GameStatus gameStatus, Long gameId);
    GameStatus get(Long gameId);
}
