package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.humans.Life;

public interface HumanService {

  /**
   * Check whether the lifeToken (representing a human) is still alive or not.
   *
   * @param lifeToken the life token.
   * @return true if alive, false otherwise.
   */
  boolean isAlive(int lifeToken);

  /**
   * Check whether humans have enough tickets left.
   *
   * @return true if yes, false otherwise
   */
  boolean hasTicketsLeft();


  /**
   * Check whether humans can spawn on map (<=> have enough players still alive).
   *
   * @return true if yes, false otherwise
   */
  boolean canSpawn();

  /**
   * Spawn a new life.
   *
   * @return true if success, false else
   */
  Life spawn();

  /**
   * getCurrent life by token.
   *
   * @param token .
   * @return life.
   */
  Life getLifeByToken(int token);

  /**
   * Save life.
   *
   * @param life .
   */
  void save(Life life);

  /**
   * Deactivate an human life by id (do not decrease gameStatus.nbLifeLeft).
   *
   * @param lifeToken the token to deactivate
   * @return true if number human left > 0, else false
   */
  boolean kill(int lifeToken);
}
