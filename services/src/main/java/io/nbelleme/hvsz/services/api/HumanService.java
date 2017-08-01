package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.human.internal.Human;

public interface HumanService {

  /**
   * Check whether the humanToken (representing a human) is still alive or not.
   *
   * @param humanToken the human token.
   * @return true if alive, false otherwise.
   */
  boolean isAlive(int humanToken);

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
   * Spawn a new human.
   *
   * @return true if success, false else
   */
  Human spawn();

  /**
   * getCurrent human by token.
   *
   * @param token .
   * @return human.
   */
  Human getHumanByToken(int token);

  /**
   * Save human.
   *
   * @param human .
   */
  void save(Human human);

  /**
   * Deactivate an human human by id (do not decrease gameStatus.nbHumanLeft).
   *
   * @param humanToken the token to deactivate
   * @return true if number human left > 0, else false
   */
  boolean kill(int humanToken);
}
