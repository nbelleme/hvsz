package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.Life;

public interface HumanService {

    /**
     * Get a new life.
     *
     * @return true if success, false else
     */
    Life newLife();

    /**
     * get life by id.
     * @param id the id of the life
     * @return the life corresponding
     */
    Life getLife(Integer id);

    /**
     * @param zId  ZoneResource id
     * @param qt Integer : resources amount
     * @param id Integer : life id
     * @return Integer : amount got
     */
    Integer getResources(Integer zId, Integer qt, Integer id);

    /**
     * Count how many life in stock.
     * @return the amount of life in stock
     */
    int countLifeLeft();
}
