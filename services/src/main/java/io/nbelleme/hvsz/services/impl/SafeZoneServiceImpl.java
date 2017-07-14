package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.humans.Life;
import io.nbelleme.hvsz.humans.SafeZone;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.SafeZoneService;
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
  public SafeZone getSafeZone(Long zoneId) {
    return getSafeZones()
        .stream()
        .filter(z -> z.getId().equals(zoneId))
        .findFirst()
        .orElse(null);
  }

  @Override
  public List<SafeZone> getSafeZones() {
    Game game = gameService.get();
    Assert.gameOngoing(game);
    return game.getSafeZones();
  }

  @Override
  public int refill(Long zoneId, int token) {
    Game game = gameService.get();
    SafeZone safeZone = game.getSafeZones()
        .stream()
        .filter(z -> z.getId().equals(zoneId))
        .findFirst()
        .orElse(null);

    Life life = game.getStatus()
        .getLifeByToken(token);

    Assert.humanAlive(life, safeZone);

    if (safeZone.isDestroyed()) {
      return 0;
    }
    int oldLevel = safeZone.getLevel();
    int level = Math.min(oldLevel + life.getNbResources(), safeZone.getCapacity());
    int refilled = level - oldLevel;

    safeZone.setLevel(level);
    gameService.update(game);
    return refilled;
  }

  @Override
  public void eatOneUnitOfFood() {
    Game g = gameService.get();
    g.getSafeZones().forEach(safeZone -> {
      if (safeZone.getLevel() > 0) {
        safeZone.setLevel(safeZone.getLevel() - 1);
      }
    });
    gameService.update(g);
  }

}
