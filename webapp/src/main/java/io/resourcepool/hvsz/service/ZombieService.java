package io.resourcepool.hvsz.service;

public interface ZombieService {

    /**
     * Deactivate an human life by id (do not decrease gameStatus.nbLifeLeft).
     * @param lifeId the lifeId to deactivated
     * @return true if number human left > 0, else false
     */
    boolean kill(String lifeId);
}
