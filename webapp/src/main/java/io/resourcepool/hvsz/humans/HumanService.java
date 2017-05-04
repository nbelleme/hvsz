package io.resourcepool.hvsz.humans;

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
   * get life by token.
   *
   * @param token .
   * @return life.
   */
  Life getLifeByToken(int token);
}
