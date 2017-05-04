package io.resourcepool.hvsz.humans;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SafeZoneServiceImpl implements SafeZoneService {

  @Autowired
  private GameService gameService;

  @Autowired
  private HumanService humanService;


  @Override
  public SafeZone getSafeZone(Long zoneId) {
    return getSafeZones().stream().filter(z -> z.getId().equals(zoneId)).findFirst().get();
  }

  @Override
  public List<SafeZone> getSafeZones() {
    Game g = gameService.getActive();
    Assert.gameOngoing(g);
    return g.getSafeZones();
  }

  @Override
  public int refill(Long zoneId, int token) {
    SafeZone safeZone = getSafeZone(zoneId);
    Life life = humanService.getLifeByToken(token);
    Assert.humanAlive(life);
    return safeZone.refill(life.getNbResources());
  }

  @Override
  public void eatOneUnitOfFood() {
    Game g = gameService.getActive();
    g.getSafeZones().forEach(z -> {
      if (z.getLevel() > 0) {
        z.setLevel(z.getLevel() - 1);
      }
    });
    gameService.update(g);
  }

}
