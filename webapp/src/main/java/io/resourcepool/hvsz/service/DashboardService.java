package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.ZoneResource;

import java.util.List;

/**
 * Created by ebiz on 27/04/17.
 */
public interface DashboardService {
    /**
     * Get amount of life left for human.
     *
     * @return the amount of life left
     */
    int getLifeLeft();

    /**
     * Get the number of human in play.
     *
     * @return the number of human in play (human in start - human dead without respawn)
     */
    int getHuman();

    /**
     * Get the number of zombie in game.
     *
     * @return the number of zombie.
     */
    int getZombie();

    /**
     * Get time left.
     *
     * @return minute before the end of the game.
     */
    int getTime();

    /**
     * Get the list of ZoneResource.
     * @return the list of ZoneResource
     */
    List<ZoneResource> getZoneResource();
}
