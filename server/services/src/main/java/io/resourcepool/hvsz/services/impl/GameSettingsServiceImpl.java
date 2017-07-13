package io.resourcepool.hvsz.services.impl;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameSettings;
import io.resourcepool.persistence.dao.DaoMapDb;
import io.resourcepool.hvsz.services.api.GameService;
import io.resourcepool.hvsz.services.api.GameSettingsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static io.resourcepool.hvsz.common.Constants.GAME_ID;

@Service
final class GameSettingsServiceImpl implements GameSettingsService {

  private DaoMapDb dao;
  private GameService gameService;

  /**
   * Constructor.
   *
   * @param dao         dao
   * @param gameService gameService
   */
  GameSettingsServiceImpl(DaoMapDb dao, GameService gameService) {
    this.dao = Objects.requireNonNull(dao);
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  public void set(GameSettings gameSettings) {
    Game g = gameService.get();
    if (g == null) {
      g = new Game(GAME_ID);
    }
    g.setConfig(gameSettings);
    gameService.update(g);
  }

  @Override
  public GameSettings get() {
    Game active = gameService.get();
    Assert.gameDefined(active);
    return active.getConfig();
  }
}
