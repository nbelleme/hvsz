package io.resourcepool.hvsz.persistance.models;

/**
 * Created by root on 26/04/17.
 */
public class SupplyZone implements ZoneResource {
    private int resource = 2;
    private String name = "SupplyZone x";

    public int getResource() {
        return resource;
    }

    @Override
    public String getName() {
        return name;
    }
}
