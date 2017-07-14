package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.common.exceptions.CannotSpawnException;
import io.nbelleme.hvsz.common.exceptions.NoHumanLeftException;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.Status;
import io.nbelleme.hvsz.humans.Life;
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
    Life lifeByToken = g.getStatus().getLifeByToken(lifeToken);
    return lifeByToken.isAlive();
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
  public Life spawn() {
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
    Life life = Life.build();
    status.getLives().add(life);
    g.setStatus(status);
    gameService.update(g);
    return life;
  }

  @Override
  public Life getLifeByToken(int token) {
    Game g = gameService.getCurrent();
    Assert.gameActive(g);
    return g.getStatus().getLives().stream().filter(l -> l.getToken() == token).findFirst().orElse(null);
  }

  @Override
  public void save(Life life) {
    Game g = gameService.getCurrent();
    g.getStatus().setLife(life.getId(), life);
    gameService.update(g);
  }

  @Override
  public boolean kill(int lifeToken) {
    Game game = gameService.getCurrent();
    Status status = game.getStatus();
    Life life = game.getStatus().getLifeByToken(lifeToken);
    if (life != null && life.isAlive()) {
      life.setAlive(false);
      life.setToken(-1);
      this.save(life);

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
