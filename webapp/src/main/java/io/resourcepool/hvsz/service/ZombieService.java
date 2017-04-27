package io.resourcepool.hvsz.service;

/**
 * Created by ebiz on 27/04/17.
 */
public interface ZombieService {
    /**
     * Kill one human.
     * @return true if number human left > 0, else false
     */
    boolean kill();
}
