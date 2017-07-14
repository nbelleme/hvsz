package io.nbelleme.hvsz.services.impl;


import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.GameSettings;
import io.nbelleme.hvsz.game.GameState;
import io.nbelleme.hvsz.game.Status;
import io.nbelleme.hvsz.zone.SafeZone;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.zone.SupplyZone;
import io.nbelleme.persistence.dao.DaoMapDb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.nbelleme.hvsz.common.Constants.GAME_ID;

@Service
final class GameServiceImpl implements GameService {

  private static final long SECONDS_IN_ONE_MINUTE = 60;

  private DaoMapDb dao;

  /**
   * Constructor.
   *
   * @param daoMapDb dao
   */
  GameServiceImpl(DaoMapDb daoMapDb) {
    this.dao = Objects.requireNonNull(daoMapDb);
  }

  @Override
  public Game getCurrent() {
    return dao.get(GAME_ID);
  }

  @Override
  public void startGame() {
    //Asserts that the game is over if it exists
    Game currentGame = getCurrent();
    if (currentGame != null) {
      Assert.gameOver(currentGame);
    }

    Game game = Game.build();
    // Retrieve settings
    GameSettings conf = GameSettings.build();
    game.setConfig(conf);
    // Init game status
    Status status = Status.build()
        .setRemainingHumanTickets(conf.getHumanTickets())
        .setCurrentHumansOnField(0)
        .setRemainingTime(conf.getGameDuration() * SECONDS_IN_ONE_MINUTE)
        .setGameState(GameState.ACTIVE);

    game.setStatus(status);
    // Init game supply zones
    List<SupplyZone> foodSupplies = new ArrayList<>(conf.getNbFoodSupplyZones());
    int foodPerZone = conf.getNbFoodSupplies() / conf.getNbFoodSupplyZones();

    for (long i = 0; i < conf.getNbFoodSupplyZones(); i++) {
      SupplyZone supplyZone = SupplyZone.build()
          .setId(i)
          .setCapacity(foodPerZone)
          .setLevel(foodPerZone);
      foodSupplies.add(supplyZone);
    }
    game.setFoodSupplies(foodSupplies);
    // Init game safe zones
    List<SafeZone> safeZones = new ArrayList<>(conf.getNbSafeZones());
    int nbSafeZones = conf.getNbSafeZones();
    for (long i = 0; i < nbSafeZones; i++) {
      SafeZone safeZone = SafeZone.build()
          .setId(i)
          .setLevel(conf.getStartingSafeZoneSupplies());
      safeZones.add(safeZone);
    }
    game.setSafeZones(safeZones);
    // Save game
    update(game);
  }

  @Override
  public void pauseGame() {
    Game game = dao.get(GAME_ID);
    Assert.gameActive(game);
    game.getStatus().setGameState(GameState.PAUSED);
    update(game);
  }

  @Override
  public void resumeGame() {
    Game game = dao.get(GAME_ID);
    Assert.gamePaused(game);
    game.getStatus().setGameState(GameState.ACTIVE);
    update(game);
  }

  @Override
  public void stopGame() {
    Game game = dao.get(GAME_ID);
    Assert.gameOngoing(game);
    game.getStatus().setGameState(GameState.ZOMBIE_VICTORY);
    update(game);
  }

  @Override
  public void update(Game g) {
    Assert.gameDefined(g);
    if (allSafeZonesDestroyed(g) || noHumanLivesLeft(g)) {
      g.getStatus().setGameState(GameState.ZOMBIE_VICTORY);
    } else if (timesUp(g)) {
      g.getStatus().setGameState(GameState.HUMAN_VICTORY);
    }
    dao.set(GAME_ID, g);
  }

  /**
   * @param g the active game
   * @return true if the time is up
   */
  private boolean timesUp(Game g) {
    return g.getStatus().getRemainingTime() <= 0;
  }

  /**
   * @param g the active game
   * @return true if all human lives have been consumed
   */
  private boolean noHumanLivesLeft(Game g) {
    return g.getStatus().getRemainingHumanTickets() + g.getStatus().getCurrentHumansOnField() <= 0;
  }

  /**
   * @param g the active game
   * @return true if all safe zones were destroyed
   */
  private boolean allSafeZonesDestroyed(Game g) {
    return g.getSafeZones() == null || g.getSafeZones()
        .stream()
        .filter(z -> z.getLevel() > 0)
        .count() == 0;
  }

}
