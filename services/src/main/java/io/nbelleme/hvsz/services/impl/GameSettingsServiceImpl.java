package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.GameSettings;
import io.nbelleme.persistence.dao.DaoMapDb;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.GameSettingsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static io.nbelleme.hvsz.common.Constants.GAME_ID;

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
