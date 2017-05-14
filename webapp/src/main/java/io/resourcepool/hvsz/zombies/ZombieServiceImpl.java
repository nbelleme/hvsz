package io.resourcepool.hvsz.zombies;

import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.game.Status;
import io.resourcepool.hvsz.humans.HumanService;
import io.resourcepool.hvsz.humans.Human;
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
    Human human = humanService.getHuman(lifeToken);
    if (human != null && human.isAlive()) {
      human.setAlive(false);
      human.setToken(-1);
      humanService.save(human);
      // Decrement humans on field
      status.setCurrentHumansOnField(status.getCurrentHumansOnField() - 1);
      g.setStatus(status);
      gameService.update(g);
      return true;
    }
    return false;
  }
}
