package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.persistance.models.ZoneResource;

import java.util.List;

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
     * @param idSupplyZone the id of the supply zone where we take resources.
     * @param amount number max of resources we want to take.
     * @return the number of resources taken.
     */
    int get(int idSupplyZone, int amount);

    /**
     * Take resources on a supply zone.
     * @param safeZone the supply zone where we drop resources.
     * @param amount number max of resources we want to drop.
     * @return the number of resources dropped.
     */
    int drop(SafeZone safeZone, int amount);

    /**
     * Take resources on a supply zone.
     * @param idSafeZone the id of the supply zone where we drop resources.
     * @param amount number max of resources we want to drop.
     * @return the new status of the SafeZone.
     */
    int drop(int idSafeZone, int amount);

    /**
     * Decrease an amount on all safezones.
     * @param amount int
     */
    void decreaseSafezones(int amount);

    /**
     * Get the correponding safe zone.
     * @param safeId the id of the safe zone
     * @return the SafeZone
     */
    SafeZone getSafeZone(String safeId);

    /**
     * Get the correponding safe zone.
     * @param safeId the id of the safe zone
     * @return the SafeZone
     */
    SafeZone getSafeZone(int safeId);

    /**
     * Return all the Safe Zone.
     * @return a list of safe zone
     */
    List<SafeZone> getAllSafeZone();

    /**
     * Return all the Safe Zone and Supply Zone.
     * @return a list of ZoneResource
     */
    List<ZoneResource> getAllZoneResource();
}
