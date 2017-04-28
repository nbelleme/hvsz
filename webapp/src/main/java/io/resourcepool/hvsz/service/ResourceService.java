package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;

public interface ResourceService {

    /**
     * Take resources on a supply zone.
     * @param supplyZone the supply zone where we take resources.
     * @param amount number max of resources we want to take.
     * @return the number of resources taken.
     */
    int get(SupplyZone supplyZone, int amount);

    /**
     * Take resources on a supply zone.
     * @param safeZone the supply zone where we drop resources.
     * @param amount number max of resources we want to drop.
     * @return the number of resources dropped.
     */
    int drop(SafeZone safeZone, int amount);

    /**
     * Decrease an amount on all safezones.
     * @param amount of resource de decrease all Safe Zone
     */
    void decreaseSafezones(int amount);

    /**
     * Take resources on a supply zone by id of life.
     * @param safeZone the supply zone where we drop resources.
     * @param amount number max of resources we want to drop.
     * @param id of the life who get the resource
     * @return the amount of resource dropped
     */
    int dropById(SafeZone safeZone, int amount, int id);
}
