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
     * Drop resources on a safe zone.
     * @param safeZone the safe zone where we drop resources.
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
     * Drop resources on a safe zone by id.
     * @param safeZone the safe zone where we drop resources.
     * @param amount number max of resources we want to drop.
     * @param id life id
     * @return dropped amount
     */
    int dropById(SafeZone safeZone, int amount, int id);

    /**
     ** Drop resources on a safe zone by id.
     * @param safeZoneId the safe zone id where we drop resources.
     * @param amount qte
     * @param id life id
     * @return dropped amount
     */
    int dropById(Integer safeZoneId, int amount, int id);
}
