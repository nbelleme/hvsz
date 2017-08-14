package io.nbelleme.hvsz.services.impl;


import io.nbelleme.hvsz.common.exceptions.NoGameDefinedException;
import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.game.internal.GameState;
import io.nbelleme.hvsz.game.internal.Status;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.hvsz.zone.internal.ZoneFactory;
import io.nbelleme.persistence.dao.api.GameDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
  public Optional<Game> get(String id) {
    return gameDao.findOne(id);
  }

  @Override
  public Optional<List<Game>> getAll() {
    return gameDao.findAll();
  }

  @Override
  public Game startGame() {
    return gameDao.insert(newGame())
        .orElseThrow(NoClassDefFoundError::new);
  }

  @Override
  public Optional<Game> pause(String id) {
    Game game = gameDao.findOne(id)
                       .orElseThrow(NoGameDefinedException::new);
    game.getStatus().setState(GameState.PAUSED);
    return gameDao.save(game);
  }


  @Override
  public Optional<Game> resumeGame(String id) {
    Game game = gameDao.findOne(id)
                       .orElseThrow(NoGameDefinedException::new);
    game.getStatus().setState(GameState.ACTIVE);
    return gameDao.save(game);
  }

  @Override
  public void stopGame() {
  }

  @Override
  public Optional<Game> update(Game game) {
    return gameDao.save(game);
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
                          .setState(GameState.ACTIVE);
    game.setStatus(status);

    List<SupplyZone> supplyZones = ZoneFactory.initSupplyZones(conf);
    game.setFoodSupplies(supplyZones);

    List<SafeZone> safeZones = ZoneFactory.initSafeZones(conf);
    game.setSafeZones(safeZones);

    return game;
  }
}
