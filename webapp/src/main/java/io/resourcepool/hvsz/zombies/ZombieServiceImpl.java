package io.resourcepool.hvsz.zombies;

import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.game.Status;
import io.resourcepool.hvsz.humans.HumanService;
import io.resourcepool.hvsz.humans.Life;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZombieServiceImpl implements ZombieService {

  @Autowired
  private GameService gameService;

  @Autowired
  private HumanService humanService;

  @Override
  public boolean kill(int lifeToken) {
    Game g = gameService.get();
    Status status = g.getStatus();
    Life life = g.getStatus().getLifeByToken(lifeToken);
    if (life != null && life.isAlive()) {
      life.setAlive(false);
      life.setToken(-1);
      //humanService.save(life);
      // Decrement humans on field
      status.setCurrentHumansOnField(status.getCurrentHumansOnField() - 1);
      g.setStatus(status);
      gameService.update(g);
      return true;
    }
    return false;
  }
}
