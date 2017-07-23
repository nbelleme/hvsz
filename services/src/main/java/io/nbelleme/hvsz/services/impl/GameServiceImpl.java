package io.nbelleme.hvsz.services.impl;


import io.nbelleme.hvsz.common.AssertGame;
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
    return gameDao.get().orElse(null);
  }

  @Override
  public void startGame() {
    Game game = gameDao.get().orElse(Game.build());

    if (game.getStatus().isStopped()) {
      game = newGame();
    }

    save(game);
  }


  @Override
  public void pauseGame() {
    Game game = getCurrent();
    AssertGame.gameOngoing(game);
    updateGameState(game, GameState.PAUSED);
    save(game);
  }


  @Override
  public void resumeGame() {
    Game game = gameDao.get().orElse(null);
    AssertGame.gameReadyToStart(game);
  }

  @Override
  public void stopGame() {
    Game game = getCurrent();
    AssertGame.gameOngoing(game);
    updateGameState(game, GameState.STOPPED);
    save(game);
  }

  @Override
  public Game save(Game game) {
    //TODO check if game over
    return gameDao.save(game);
  }

  /**
   * Check and update game status if needed.
   *
   * @param game gmae
   */
  private void checkGameStatus(Game game) {

  }


  /**
   * Update game's state.
   *
   * @param game      game to update
   * @param gameState new gameState
   */
  private void updateGameState(Game game, GameState gameState) {
    Status status = game.getStatus()
                        .setGameState(gameState);
    game.setStatus(status);
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


  /**
   * Create new default game.
   *
   * @return game created
   */
  private Game newGame() {
    Game game = Game.build();

    GameSettings conf = GameSettings.build();
    game.setConfig(conf);

    Status status = Status.build()
                          .setRemainingHumanTickets(conf.getHumanTickets())
                          .setCurrentHumansOnField(0)
                          .setRemainingTime(conf.getGameDuration() * SECONDS_IN_ONE_MINUTE)
                          .setGameState(GameState.ACTIVE);
    game.setStatus(status);

    List<SupplyZone> supplyZones = ZoneFactory.initSupplyZones(conf);
    game.setFoodSupplies(supplyZones);

    List<SafeZone> safeZones = ZoneFactory.initSafeZones(conf);
    game.setSafeZones(safeZones);

    return game;
  }


}
