package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.SupplyZone;

public interface HumanService {

    /**
     * Get a new life.
     * @return true if success, false else
     */
    Integer newLife();

    /**
     * get life by id.
     * @param id the id of the life
     * @return the life corresponding
     */
    Life getLife(Integer id);

    /**
     *
     * @param z ZoneResource
     * @param qt Integer : resources amount
     * @param id Integer : life id
     * @return Integer : amount got
     */
    Integer getResources(SupplyZone z, Integer qt, Integer id);
}
