package io.resourcepool.hvsz.services.impl;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.humans.Life;
import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.services.api.GameService;
import io.resourcepool.hvsz.services.api.HumanService;
import io.resourcepool.hvsz.services.api.SafeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SafeZoneServiceImpl implements SafeZoneService {

  private GameService gameService;
  private HumanService humanService;

  public SafeZoneServiceImpl(GameService gameService, HumanService humanService) {
    this.gameService = Objects.requireNonNull(gameService);
    this.humanService = Objects.requireNonNull(humanService);
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
    Game g = gameService.get();
    Assert.gameOngoing(g);
    return g.getSafeZones();
  }

  @Override
  public int refill(Long zoneId, int token) {
    Game g = gameService.get();
    SafeZone safeZone = g.getSafeZones()
        .stream()
        .filter(z -> z.getId().equals(zoneId))
        .findFirst()
        .orElse(null);
    Life life = g.getStatus().getLifeByToken(token);
    Assert.humanAlive(life, safeZone);
    int refilled = safeZone.refill(life.dropAllResources());
    gameService.update(g);
    return refilled;
  }

  @Override
  public void eatOneUnitOfFood() {
    Game g = gameService.get();
    g.getSafeZones().forEach(z -> {
      if (z.getLevel() > 0) {
        z.setLevel(z.getLevel() - 1);
      }
    });
    gameService.update(g);
  }

}
