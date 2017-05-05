package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.persistence.dao.DaoMapDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.resourcepool.hvsz.common.Constants.GAME_ID;

@Service
public class GameSettingsServiceImpl implements GameSettingsService {

  @Autowired
  private DaoMapDb dao;

  @Autowired
  private GameService gameService;

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
