package io.resourcepool.hvsz.persistance.models;

/**
 * Created by root on 26/04/17.
 */
public class SafeZone implements ZoneResource {
    private int resource = 5;
    private String name = "SafeZone x";

    public int getResource() {
        return resource;
    }

    @Override
    public String getName() {
        return name;
    }
}
