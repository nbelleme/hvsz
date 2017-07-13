package io.resourcepool.hvsz.services.impl;


import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameSettings;
import io.resourcepool.hvsz.game.GameState;
import io.resourcepool.hvsz.game.Status;
import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.persistence.dao.DaoMapDb;
import io.resourcepool.hvsz.services.api.GameService;
import io.resourcepool.hvsz.supply.FoodSupply;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.resourcepool.hvsz.common.Constants.GAME_ID;

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
  public Game get() {
    return dao.get(GAME_ID);
  }

  @Override
  public void startGame() {
    //Asserts that the game is over if it exists
    Game currentGame = get();
    if (currentGame != null) {
      Assert.gameOver(currentGame);
    }

    Game game = new Game();
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
    List<FoodSupply> foodSupplies = new ArrayList<>(conf.getNbFoodSupplyZones());
    int foodPerZone = conf.getNbFoodSupplies() / conf.getNbFoodSupplyZones();

    for (long i = 0; i < conf.getNbFoodSupplyZones(); i++) {
      foodSupplies.add(new FoodSupply(i, foodPerZone, foodPerZone));
    }
    game.setFoodSupplies(foodSupplies);
    // Init game safe zones
    List<SafeZone> safeZones = new ArrayList<>(conf.getNbSafeZones());
    int nbSafeZones = conf.getNbSafeZones();
    for (long i = 0; i < nbSafeZones; i++) {
      safeZones.add(new SafeZone(i, conf.getStartingSafeZoneSupplies(), 100));
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
