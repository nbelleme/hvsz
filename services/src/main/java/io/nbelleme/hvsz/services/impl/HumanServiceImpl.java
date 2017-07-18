package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.common.exceptions.CannotSpawnException;
import io.nbelleme.hvsz.common.exceptions.NoHumanLeftException;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.Status;
import io.nbelleme.hvsz.humans.Human;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.HumanService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
final class HumanServiceImpl implements HumanService {

  private Long nextId = 0L;

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  HumanServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
  }

  /**
   * @return the next integer. Warning: this call is blocking.
   */
  private synchronized Long nextId() {
    return nextId++;
  }

  @Override
  public boolean isAlive(int lifeToken) {
    Game g = gameService.getCurrent();
    Human humanByToken = g.getStatus().getLifeByToken(lifeToken);
    return humanByToken.isAlive();
  }

  @Override
  public boolean hasTicketsLeft() {
    Game g = gameService.getCurrent();
    Assert.gameActive(g);
    return g.getStatus().getRemainingHumanTickets() > 0;

  }

  @Override
  public boolean canSpawn() {
    Game g = gameService.getCurrent();
    return hasTicketsLeft() && g.getStatus().getCurrentHumansOnField() < g.getStatus().getMaxHumansOnField();
  }

  @Override
  public Human spawn() {
    Game g = gameService.getCurrent();
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
    Human human = Human.build();
    status.getLives().add(human);
    g.setStatus(status);
    gameService.update(g);
    return human;
  }

  @Override
  public Human getHumanByToken(int token) {
    Game g = gameService.getCurrent();
    Assert.gameActive(g);
    return g.getStatus().getLives().stream().filter(l -> l.getToken() == token).findFirst().orElse(null);
  }

  @Override
  public void save(Human human) {
    Game g = gameService.getCurrent();
    g.getStatus().setLife(human.getId(), human);
    gameService.update(g);
  }

  @Override
  public boolean kill(int lifeToken) {
    Game game = gameService.getCurrent();
    Status status = game.getStatus();
    Human human = game.getStatus().getLifeByToken(lifeToken);
    if (human != null && human.isAlive()) {
      human.setAlive(false);
      human.setToken(-1);
      this.save(human);

      // Decrement humans on field
      status.setCurrentHumansOnField(status.getCurrentHumansOnField() - 1);
      game.setStatus(status);
      gameService.update(game);
      return true;
    }
    return false;
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
