package io.resourcepool.hvsz.services.api;

public interface ZombieService {

  /**
   * Deactivate an human life by id (do not decrease gameStatus.nbLifeLeft).
   *
   * @param lifeToken the token to deactivate
   * @return true if number human left > 0, else false
   */
  boolean kill(int lifeToken);
}
