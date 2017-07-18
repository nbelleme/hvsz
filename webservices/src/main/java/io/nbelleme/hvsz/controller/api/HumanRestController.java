package io.nbelleme.hvsz.controller.api;

import io.nbelleme.hvsz.humans.Human;

public interface HumanRestController {
  /**
   * Kill human by token.
   *
   * @param token human token.
   * @return bool succes?
   */
  Boolean kill(Integer token);

  /**
   * Get a new human, return token.
   *
   * @return human token
   */
  Human takeHuman();

  /**
   * Get resource number by human.
   *
   * @param humanToken the token of the human
   * @return the number of resources taken
   */
  int getNbResourceByHuman(int humanToken);

  /**
   * Get remaining lives.
   *
   * @return the number of remaining live
   */
  int getRemainingLives();

  /**
   * Get current number of human on field.
   *
   * @return the current number of human on field
   */
  int getActiveLives();

}
