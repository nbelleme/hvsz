package io.resourcepool.hvsz.humans;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.common.exceptions.CannotSpawnException;
import io.resourcepool.hvsz.common.exceptions.NoHumanLeftException;
import io.resourcepool.hvsz.common.models.GenericBuilder;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.Status;
import io.resourcepool.hvsz.persistence.dao.DaoMapDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.resourcepool.hvsz.common.Constants.GAME_ID;

@Service
public class HumanServiceImpl implements HumanService {

  private Long nextId = 0L;

  @Autowired
  private DaoMapDb dao;


  /**
   * @return the next integer. Warning: this call is blocking.
   */
  private synchronized Long nextId() {
    return nextId++;
  }

  @Override
  public boolean isAlive(int lifeToken) {
    Game g = dao.get(GAME_ID);
    Life lifeByToken = g.getStatus().getLifeByToken(lifeToken);
    return lifeByToken.isAlive();
  }

  @Override
  public boolean hasTicketsLeft() {
    Game g = dao.get(GAME_ID);
    Assert.gameActive(g);
    return g.getStatus().getRemainingHumanTickets() > 0;

  }

  @Override
  public boolean canSpawn() {
    Game g = dao.get(GAME_ID);
    return hasTicketsLeft() && g.getStatus().getCurrentHumansOnField() < g.getStatus().getMaxHumansOnField();
  }

  @Override
  public Life spawn() {
    Game g = dao.get(GAME_ID);
    Assert.gameActive(g);
    Status status = g.getStatus();

    if (!hasTicketsLeft()) {
      throw new NoHumanLeftException();
    }
    if (!canSpawn()) {
      throw new CannotSpawnException();
    }
    // Decrement nbLifeLeft in game status and increment nbHumanAlive
    status.setRemainingHumanTickets(status.getRemainingHumanTickets() - 1);
    status.setCurrentHumansOnField(status.getCurrentHumansOnField() + 1);
    // Create new Life
    Long id = nextId();
    Life life = GenericBuilder.of(Life::new)
      .with(Life::setId, nextId())
      .with(Life::setAlive, true)
      .with(Life::setNbResources, 0)
      .with(Life::setToken, generateToken(id))
      .build();
    status.getLives().add(life);
    g.setStatus(status);
    dao.set(GAME_ID, g);
    return life;
  }

  @Override
  public Life getLifeByToken(int token) {
    Game g = dao.get(GAME_ID);
    Assert.gameActive(g);
    return g.getStatus().getLives().stream().filter(l -> l.getToken() == token).findFirst().get();
  }

  /**
   * Generate random token from id.
   *
   * @param id the id to use as seed
   * @return the generated token.
   */
  private int generateToken(Long id) {
    int token = (int) ((id % 10) * 10_000);
    token += Math.random() * 10_000;
    return token;
  }

}
