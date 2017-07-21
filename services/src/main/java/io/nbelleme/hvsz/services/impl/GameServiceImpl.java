package io.nbelleme.hvsz.services.impl;


import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.GameSettings;
import io.nbelleme.hvsz.game.GameState;
import io.nbelleme.hvsz.game.Status;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.zone.SafeZone;
import io.nbelleme.hvsz.zone.SupplyZone;
import io.nbelleme.persistence.dao.api.GameDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
final class GameServiceImpl implements GameService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
  private static final long SECONDS_IN_ONE_MINUTE = 60;
  private GameDao gameDao;


  /**
   * Constructor.
   *
   * @param gameDao gameDao
   */
  GameServiceImpl(GameDao gameDao) {
    this.gameDao = Objects.requireNonNull(gameDao);
  }

  @Override
  public Game getCurrent() {
    LOGGER.info("GameServiceImpl getCurrent");
    return gameDao.get().orElse(Game.build());
  }

  @Override
  public void startGame() {
    LOGGER.info("Start Game");
    //TODO use currentGame
    //Asserts that the game is over if it exists

    Game game = gameDao.get().orElse(Game.build());

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
    save(game);
  }

  @Override
  public void pauseGame() {
  }

  @Override
  public void resumeGame() {
  }

  @Override
  public void stopGame() {
  }

  @Override
  public Game save(Game game) {
    //TODO check if game over
    return gameDao.save(game);
  }

  /**
   * @param game the active game
   * @return true if the time is up
   */
  private boolean timesUp(Game game) {
    return game.getStatus().getRemainingTime() <= 0;
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
