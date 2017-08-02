package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.human.internal.Human;
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
    return false;
  }

  @Override
  public boolean hasTicketsLeft() {
    return false;
  }

  @Override
  public boolean canSpawn() {
    return false;
  }

  @Override
  public Human spawn() {
    return null;
  }

  @Override
  public Human getHumanByToken(int token) {
    return null;
  }

  @Override
  public void save(Human human) {
  }

  @Override
  public boolean kill(int lifeToken) {
    return false;
  }

}
