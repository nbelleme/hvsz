package io.resourcepool.hvsz.persistance.models;

public interface ZoneResource extends Zone {

    /**
     * Get the zone resource.
     * @return int
     */
    int getResource();

    /**
     * Get the id of the zone.
     * @return int
     */
    Integer getId();
}
