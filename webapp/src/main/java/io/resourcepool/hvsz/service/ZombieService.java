package io.resourcepool.hvsz.service;

public interface ZombieService {

    /**
     * Kill an human.
     * @return true if number human left > 0, else false
     */
    boolean kill();
}
