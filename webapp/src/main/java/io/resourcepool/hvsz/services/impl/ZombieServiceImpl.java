package io.resourcepool.hvsz.services.impl;

import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.Status;
import io.resourcepool.hvsz.services.api.GameService;
import io.resourcepool.hvsz.services.api.HumanService;
import io.resourcepool.hvsz.humans.Life;
import io.resourcepool.hvsz.services.api.ZombieService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ZombieServiceImpl implements ZombieService {

  private GameService gameService;

  private HumanService humanService;

  public ZombieServiceImpl(GameService gameService, HumanService humanService) {
    this.gameService = Objects.requireNonNull(gameService);
    this.humanService = Objects.requireNonNull(humanService);
  }

  @Override
  public boolean kill(int lifeToken) {
    Game g = gameService.get();
    Status status = g.getStatus();
    Life life = g.getStatus().getLifeByToken(lifeToken);
    if (life != null && life.isAlive()) {
      life.setAlive(false);
      life.setToken(-1);
      humanService.save(life);

      // Decrement humans on field
      status.setCurrentHumansOnField(status.getCurrentHumansOnField() - 1);
      g.setStatus(status);
      gameService.update(g);
      return true;
    }
    return false;
  }
}
