package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.AssertGame;
import io.nbelleme.hvsz.common.AssertHuman;
import io.nbelleme.hvsz.common.AssertSafeZone;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.humans.Human;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.SafeZoneService;
import io.nbelleme.hvsz.zone.SafeZone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
final class SafeZoneServiceImpl implements SafeZoneService {

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  SafeZoneServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  public SafeZone getSafeZone(long zoneId) {
    return getSafeZones()
        .stream()
        .filter(z -> z.getId().equals(zoneId))
        .findFirst()
        .orElse(null);
  }

  @Override
  public List<SafeZone> getSafeZones() {
    Game game = gameService.getCurrent();
    AssertGame.gameOngoing(game);
    return game.getSafeZones();
  }

  @Override
  public int refill(long zoneId, int token) {
    Game game = gameService.getCurrent();
    AssertGame.gameOngoing(game);

    SafeZone safeZone = game.getSafeZones()
                            .stream()
                            .filter(z -> z.getId().equals(zoneId))
                            .findFirst()
                            .orElse(null);

    AssertSafeZone.zoneNotDestroyed(safeZone);

    Human human = game.getStatus()
                      .getLifeByToken(token);

    AssertHuman.humanAlive(human, safeZone);


    int oldLevel = safeZone.getLevel();
    int level = Math.min(oldLevel + human.getNbResources(), safeZone.getCapacity());
    int refilled = level - oldLevel;

    safeZone.setLevel(level);
    gameService.save(game);
    return refilled;
  }

  @Override
  public void decreaseFoodLevel(Game game) {
    gameService.getCurrent();
    game.getSafeZones().forEach(safeZone -> {
      if (safeZone.getLevel() > 0) {
        safeZone.setLevel(safeZone.getLevel() - 1);
      }
    });
  }

}
