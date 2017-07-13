package io.resourcepool.hvsz.controller.api;

import io.resourcepool.hvsz.humans.Life;

public interface HumainRestController {
  /**
   * Kill life by token.
   *
   * @param token life token.
   * @return bool succes?
   */
  Boolean kill(Integer token);

  /**
   * Get a new life, return token.
   *
   * @return life token
   */
  Life takeLife();

  /**
   * Get resource number by life.
   *
   * @param lifeToken the token of the human
   * @return the number of resources taken
   */
  int getNbResourceByLife(int lifeToken);

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
