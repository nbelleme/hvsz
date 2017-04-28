package io.resourcepool.hvsz.service;

public interface ZombieService {

    /**
     * Kill an human.
     * @param lifeId lifeId
     * @return true if number human left > 0, else false
     */
    boolean kill(String lifeId);
}
