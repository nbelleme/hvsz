package io.resourcepool.hvsz.humans;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.common.exceptions.CannotSpawnException;
import io.resourcepool.hvsz.common.exceptions.NoHumanLeftException;
import io.resourcepool.hvsz.common.models.GenericBuilder;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.game.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanServiceImpl implements HumanService {

  private Long nextId = 0L;

  @Autowired
  private GameService gameService;


  /**
   * @return the next integer. Warning: this call is blocking.
   */
  private synchronized Long nextId() {
    return nextId++;
  }

  @Override
  public boolean isAlive(int lifeToken) {
    Game g = gameService.get();
    Human humanByToken = g.getStatus().getLifeByToken(lifeToken);
    return humanByToken.isAlive();
  }

  @Override
  public boolean hasTicketsLeft() {
    Game g = gameService.get();
    Assert.gameActive(g);
    return g.getStatus().getRemainingHumanTickets() > 0;

  }

  @Override
  public boolean canSpawn() {
    Game g = gameService.get();
    return hasTicketsLeft() && g.getStatus().getCurrentHumansOnField() < g.getStatus().getMaxHumansOnField();
  }

  @Override
  public Human spawn() {
    Game g = gameService.get();
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
    // Create new Human
    Long id = nextId();
    Human human = GenericBuilder.of(Human::new)
      .with(Human::setId, nextId())
      .with(Human::setAlive, true)
      .with(Human::setNbResources, 0)
      .with(Human::setToken, generateToken(id))
      .build();
    status.getLives().add(human);
    g.setStatus(status);
    gameService.update(g);
    return human;
  }

  @Override
  public Human getHuman(int token) {
    Game g = gameService.get();
    Assert.gameActive(g);
    return g.getStatus().getLives().stream().filter(l -> l.getToken() == token).findFirst().orElseGet(() -> null);
  }

  @Override
  public void save(Human human) {
    Game g = gameService.get();
    g.getStatus().setLife(human.getId(), human);
    gameService.update(g);
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
